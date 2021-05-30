package com.xum.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener extends AnalysisEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(ExcelListener.class);

    //可以通过实例获取该值
    private List<Object> datas = new ArrayList<Object>();

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        datas.add(o);//数据存储到list，供批量处理，或后续自己业务逻辑处理。
        doSomething(o);//根据自己业务做处理
    }

    private void doSomething(Object object) {
        ExcelDevices excelDevices = (ExcelDevices)object;
        LOG.info(String.format("id:%s, tvRoomId:%s", excelDevices.getId(), excelDevices.getTvroomid()));
    }

    public List<Object> getDatas() {
        return datas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        datas.clear();//解析结束销毁不用的资源
        LOG.info("upload data success, import success");
    }
}
