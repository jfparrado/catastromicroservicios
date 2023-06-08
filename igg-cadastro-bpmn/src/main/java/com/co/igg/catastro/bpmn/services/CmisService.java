package com.co.igg.catastro.bpmn.services;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.OperationContext;
import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.apache.chemistry.opencmis.client.api.Relationship;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.BaseTypeId;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.RelationshipDirection;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

/**
 * CMIS Service to handle operations within the session.
 * 
 * @author 
 *
 */
@Service
public class CmisService
{

    // Set values from "application.properties" file
    @Value("${alfresco.repository.url}")
    String alfrescoUrl;
    @Value("${alfresco.repository.user}")
    String alfrescoUser;
    @Value("${alfresco.repository.pass}")
    String alfrescoPass;

    // CMIS living session
    private Session session;


	@PostConstruct
    public void init()
    {

        String alfrescoBrowserUrl = alfrescoUrl + "/api/-default-/public/cmis/versions/1.1/browser";

        Map<String, String> parameter = new HashMap<String, String>();

        parameter.put(SessionParameter.USER, alfrescoUser);
        parameter.put(SessionParameter.PASSWORD, alfrescoPass);

        parameter.put(SessionParameter.BROWSER_URL, alfrescoBrowserUrl);
        parameter.put(SessionParameter.BINDING_TYPE, BindingType.BROWSER.value());

        SessionFactory factory = SessionFactoryImpl.newInstance();
        session = factory.getRepositories(parameter).get(0).createSession();

    }
    
    public Folder getRootFolder()
    {
        return session.getRootFolder();
    }

    public Folder getDocLibFolder( String siteName, String folder) {
        String path = "/Sites/" + siteName + "/documentLibrary" + folder;
        return (Folder) session.getObjectByPath(path);
    }
    
    public Folder createFolderIfNotExists(Folder parentFolder, String folderName) {
        Folder subFolder = null;
        for(CmisObject child : parentFolder.getChildren()) {
            if(folderName.equalsIgnoreCase(child.getName())) {
                subFolder = (Folder) child;
            }
        }

        if(subFolder == null) {
            Map<String, Object> props = new HashMap<>();
            props.put("cmis:objectTypeId", "cmis:folder");
            props.put("cmis:name", folderName);

            subFolder = parentFolder.createFolder(props);
        }
        return subFolder; 
    }
    
    public Document createDocument(Folder folder, String documentName, Integer solicitud) throws IOException, SAXException, TikaException
    {
    	File file = new File("/opt/files/catastro/"+documentName);
    	
    	//Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler(-1);
        
    	Metadata metadata = new Metadata();
    	ParseContext context = new ParseContext();
    	InputStream  inputstream = new FileInputStream(file);
        //ParseContext context = new ParseContext();
      //document parsing using PDF parser
    	
    	AutoDetectParser parser = new AutoDetectParser(); 
    	parser.parse(inputstream, handler, metadata, context);
        System.out.println( "metadata elements and values of the given file :");
        
        String[] metadataNamesb4 = metadata.names();
        System.out.println(metadata.get("xmpTPg:NPages"));
        for(String name : metadataNamesb4) {
      	  System.out.println(name + ": " + metadata.get(name));
        }
        handler.startDocument();
        String nameDocument = solicitud + "_" + System.currentTimeMillis() + "_" + documentName;
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
        properties.put(PropertyIds.NAME, nameDocument);
        
        PDDocument document = PDDocument.load(file);
		PDFTextStripper stripper = new PDFTextStripper();
		
		Map map = new HashMap<>();
		for (int i=0; i< Integer.parseInt(metadata.get("xmpTPg:NPages")); i++) {
			stripper.setStartPage(i);
			stripper.setEndPage(i);
		}
        
        FileInputStream inputstream2 = new FileInputStream(file);
        
        ContentStream contentStream = new ContentStreamImpl(
        		nameDocument, 
        		BigInteger.valueOf(file.length()),
        		metadata.get("Content-Type"), inputstream2);

        return folder.createDocument(properties, contentStream, VersioningState.MAJOR);
    }

    public ObjectId createRelationship(CmisObject sourceObject, CmisObject targetObject, String relationshipName)
    {

        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(PropertyIds.NAME, "a new relationship");
        properties.put(PropertyIds.OBJECT_TYPE_ID, relationshipName);
        properties.put(PropertyIds.SOURCE_ID, sourceObject.getId());
        properties.put(PropertyIds.TARGET_ID, targetObject.getId());

        return session.createRelationship(properties);

    }

    public void addAspect(CmisObject cmisObject, String aspect)
    {

        List<Object> aspects = cmisObject.getProperty("cmis:secondaryObjectTypeIds").getValues();
        if (!aspects.contains(aspect))
        {
            aspects.add(aspect);
            Map<String, Object> aspectListProps = new HashMap<String, Object>();
            aspectListProps.put(PropertyIds.SECONDARY_OBJECT_TYPE_IDS, aspects);
            cmisObject.updateProperties(aspectListProps);
        }

    }

    public void updateProperties(CmisObject cmisObject, Map<String, Object> properties)
    {
        cmisObject.updateProperties(properties);
    }

    public ItemIterable<Relationship> getRelationships(ObjectId objectId, String relationshipName)
    {

        ObjectType typeDefinition = session.getTypeDefinition(relationshipName);
        OperationContext operationContext = session.createOperationContext();
        return session.getRelationships(objectId, true, RelationshipDirection.EITHER, typeDefinition, operationContext);

    }

    public ItemIterable<QueryResult> query(String query)
    {
        return session.query(query, false);
    }

    public void remove(CmisObject object)
    {

        if (BaseTypeId.CMIS_FOLDER.equals(object.getBaseTypeId()))
        {
            Folder folder = (Folder) object;
            ItemIterable<CmisObject> children = folder.getChildren();
            for (CmisObject child : children)
            {
                remove(child);
            }
        }
        session.delete(object);
    }

}
