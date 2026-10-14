package tw.edu.ntub.imd.birc.coursemanagement.service.transformer.impl;

import org.springframework.stereotype.Component;
import tw.edu.ntub.imd.birc.coursemanagement.Bean.ReaderBean;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.Reader;
import tw.edu.ntub.imd.birc.coursemanagement.service.impl.BaseServiceImpl;
import tw.edu.ntub.imd.birc.coursemanagement.service.transformer.BeanEntityTransformer;
import tw.edu.ntub.imd.birc.coursemanagement.service.transformer.ReaderTransformer;

import javax.annotation.Nonnull;

@Component
public class ReaderTransformerImpl implements ReaderTransformer{
    @Nonnull
    @Override
    public Reader transferToEntity(@Nonnull ReaderBean bean){
        Reader reader = new Reader();
        reader.setId(bean.getId());
        reader.setName(bean.getName());
        reader.setEmail(bean.getEmail());
        return reader;
    }

    @Nonnull
    @Override
    public ReaderBean transferToBean(@Nonnull Reader reader){
        ReaderBean readerBean = new ReaderBean();
        readerBean.setId(reader.getId());
        readerBean.setName(reader.getName());
        readerBean.setEmail(reader.getEmail());
        return readerBean;
    }
}
