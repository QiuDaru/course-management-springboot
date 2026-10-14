package tw.edu.ntub.imd.birc.coursemanagement.service.transformer.impl;

import tw.edu.ntub.birc.common.util.JavaBeanUtils;
import tw.edu.ntub.imd.birc.coursemanagement.Bean.BookBean;
import tw.edu.ntub.imd.birc.coursemanagement.Bean.ReaderBean;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.Book;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.Reader;
import tw.edu.ntub.imd.birc.coursemanagement.service.transformer.BookTransformer;

import javax.annotation.Nonnull;

public class BookTransformerImpl implements BookTransformer {
    @Override
    public Book transferToEntity( BookBean bean) {
        return JavaBeanUtils.copy(bean, new  Book());
    }

    @Override
    public  BookBean transferToBean( Book  book) {
        return JavaBeanUtils.copy( book, new  BookBean());
    }
}
