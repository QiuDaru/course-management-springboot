package tw.edu.ntub.imd.birc.coursemanagement.service.impl;

import org.springframework.stereotype.Service;
import tw.edu.ntub.imd.birc.coursemanagement.Bean.ReaderBean;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.dao.ReaderDAO;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.Reader;
import tw.edu.ntub.imd.birc.coursemanagement.exception.DuplicateCreateException;
import tw.edu.ntub.imd.birc.coursemanagement.service.ReaderService;
import tw.edu.ntub.imd.birc.coursemanagement.service.transformer.ReaderTransformer;
import tw.edu.ntub.imd.birc.coursemanagement.service.transformer.impl.ReaderTransformerImpl;


@Service
public class ReaderServiceImpl
        extends BaseServiceImpl<ReaderBean, Reader,String>
        implements ReaderService {

    private final ReaderTransformer readerTransformer;
    private final ReaderDAO readerDAO;

    public ReaderServiceImpl(ReaderDAO readerDAO,ReaderTransformer readerTransformer){
        super(readerDAO, readerTransformer);
        this.readerDAO = readerDAO;
        this.readerTransformer = readerTransformer;

    }

    @Override
    public ReaderBean save(ReaderBean readerBean) {
        if(readerDAO.existsById(readerBean.getId())){
            throw new DuplicateCreateException("讀者編號重複");
        }
        Reader saved = readerDAO.save(readerTransformer.transferToEntity(readerBean));
        return readerTransformer.transferToBean(saved);
    }
}
