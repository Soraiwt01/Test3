package sit.int221.announcement.utils;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import sit.int221.announcement.dtos.PageDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ListMapper {
    private static final ListMapper listMapper = new ListMapper();
    private ListMapper() {}

    public <S,T> List<T> mapList(List<S> source, Class<T> targetClass, ModelMapper mapper) {
        return source.stream().map(entity -> mapper.map(entity, targetClass)).collect(Collectors.toList());
    }

    public static ListMapper getInstance() {
        return listMapper;
    }
    public <S,T> PageDTO<T> toPageDTO(Page<S> source, Class<T> targetClass, ModelMapper mapper) {
        PageDTO<T> page = mapper.map(source,PageDTO.class);
        page.setContent(mapList(source.getContent(),targetClass,mapper));
        return page;
    }

}
