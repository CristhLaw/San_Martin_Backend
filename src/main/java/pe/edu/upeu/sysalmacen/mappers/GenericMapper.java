package pe.edu.upeu.sysalmacen.mappers;


import java.util.List;

public interface GenericMapper<D, E> {

    // De entidad a DTO
    D toDTO(E entity);
    // De DTO a entidad
    E toEntity(D dto);
    // Lista de entidades a lista de DTOs
    List<D> toDTOs(List<E> entities);
    // Lista de DTOs a lista de entidades
    List<E> toEntities(List<D> dtos);
}