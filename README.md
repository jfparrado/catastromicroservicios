Para obtener los datos del cluster en camunda

1. ve al tab de clusters y crea o selecciona el cluster que neesitas. si lo creas tienes que descargar las variables
2. luego de ser creado el cluster slecciona el nombre y en el tab 2 ve a API ahi le vas a dar en create new client y ahi seleccionas todas las opciones y lo creas
3. luego tienes que descargar Spring Boot Yalm y Env Vars
4. la informacion de sprigboot yalm ponerla dentro de igg-cadastro-bpmn/src/main/resources/application.yml
5. en el archivo bpmn. asegurarse de que las bpmn:serviceTask tengan al ginal dentro del tag camunda:expression="${'service2'.toLowerCase()}" . por ejemplo "    <bpmn:serviceTask id="service22" name="service2" camunda:expression="${'service2'.toLowerCase()}">"
