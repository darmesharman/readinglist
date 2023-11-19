package readingList.mapper;

public interface Mapper<ENTITY, DTO> {
    ENTITY toEntity(final DTO dto);

    DTO toDTO(final ENTITY entity);
}
