package pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories;

import pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories.mysql.TitleEntity;
import pt.psoft.g1.psoftg1.bookmanagement.model.Title;

public class TittleMapper {
    public static TitleEntity toEntity(Title title) {
        TitleEntity titleEntity = new TitleEntity();
        titleEntity.setTitle(title.getTitle());
        return titleEntity;
    }

    public static Title toModel( TitleEntity entity) {
        Title title= new Title();
        title.setTitle(entity.getTitle());
        return title;
    }

}
