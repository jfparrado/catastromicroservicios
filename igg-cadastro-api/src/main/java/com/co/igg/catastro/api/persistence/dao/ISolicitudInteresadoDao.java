package com.co.igg.catastro.api.persistence.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.co.igg.catastro.common.models.SolicitudInteresado;

public interface ISolicitudInteresadoDao extends PagingAndSortingRepository<SolicitudInteresado, Long>{
    // Esta interfaz define la capa de acceso a datos para la entidad SolicitudInteresado

    // Extiende la interfaz PagingAndSortingRepository de Spring Data
    // para obtener métodos de operaciones CRUD básicas y soporte para paginación y ordenamiento
    // Se parametriza con la entidad SolicitudInteresado y el tipo de dato de la clave primaria (Long)
}
