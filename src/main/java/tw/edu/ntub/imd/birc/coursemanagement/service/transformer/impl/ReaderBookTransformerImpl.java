package tw.edu.ntub.imd.birc.coursemanagement.service.transformer.impl;

import org.springframework.stereotype.Component;
import tw.edu.ntub.birc.common.util.JavaBeanUtils;
import tw.edu.ntub.imd.birc.coursemanagement.Bean.ReaderBookOrderBean;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.ReaderBook;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.ReaderBookId;
import tw.edu.ntub.imd.birc.coursemanagement.service.transformer.ReaderBookTransformer;

import javax.annotation.Nonnull;

@Component
public class ReaderBookTransformerImpl implements ReaderBookTransformer {

    @Nonnull
    @Override
    public ReaderBook transferToEntity(@Nonnull ReaderBookOrderBean readerBookOrderBean) {
        ReaderBook readerBook = new ReaderBook();
        ReaderBookId readerBookId = new ReaderBookId();

        readerBookId.setBookId(readerBookOrderBean.getBookId());
        readerBookId.setReaderId(readerBookOrderBean.getReaderId());

        readerBook.setReaderBookId(readerBookId);

        return  readerBook;

    }

    @Nonnull
    @Override
    public ReaderBookOrderBean transferToBean(@Nonnull ReaderBook readerBook) {
        ReaderBookOrderBean readerBookOrderBean = new ReaderBookOrderBean();

        readerBookOrderBean.setBookId(readerBook.getBook().getId());
        readerBookOrderBean.setReaderId(readerBook.getReader().getId());
        readerBookOrderBean.setBorrowDate(readerBook.getBorrowDate());

        return readerBookOrderBean;
    }
}
