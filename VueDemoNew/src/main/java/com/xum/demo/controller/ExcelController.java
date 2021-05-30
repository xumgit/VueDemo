package com.xum.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.xum.demo.excel.ExcelDevices;
import com.xum.demo.excel.ExcelListener;
import com.xum.demo.pojo.Devices;
import com.xum.demo.service.DevicesService;
import com.xum.demo.utils.CommonUtils;
import com.xum.demo.utils.CommonVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
  cd到VueDemo这个目录
  mvn clean & mvn install & java -jar ./target/demo-0.0.1-SNAPSHOT.jar
 */
@Controller
@RequestMapping(value = "/excel")
public class ExcelController {

    private static final Logger LOG = LoggerFactory.getLogger(ExcelController.class);

    @Autowired
    DevicesService devicesService;

    @RequestMapping(value = "/vue")
    public String indexVue() {
        return "excel/index";
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "export/index";
    }

    @RequestMapping(value = "/export")
    @ResponseBody
    public String export(HttpServletResponse response) {
        String status = "{\"status\" : \"200\", \"msg\" : \"sucess\"}";
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            String fileName = new String(("Devices-" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                   .getBytes(), "UTF-8");
            List<Devices> listDevices = devicesService.selectAllByList();
            List<ExcelDevices> listExcelDevices = new ArrayList<ExcelDevices>();
            ExcelDevices excelDevices = null;
            for (Devices d : listDevices) {
                excelDevices = new ExcelDevices();
                excelDevices.setId(d.getId());
                excelDevices.setTvname(d.getTvname());
                excelDevices.setTvmodelnumber(d.getTvmodelnumber());
                excelDevices.setTvserialnumber(d.getTvserialnumber());
                excelDevices.setTvroomid(d.getTvroomid());
                excelDevices.setTvmacaddress(d.getTvmacaddress());
                excelDevices.setTvipaddress(d.getTvipaddress());
                listExcelDevices.add(excelDevices);
            }
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            EasyExcel.write(outputStream, ExcelDevices.class)
                    .sheet("第一个")
                    .doWrite(listExcelDevices);
        } catch (IOException ioe) {
            status = "{\"status\" : \"100\", \"msg\" : \"failed\"}";
            LOG.error("IoeException:" + ioe.getMessage());
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                status = "{\"status\" : \"100\", \"msg\" : \"failed\"}";
                LOG.error("IOE:" + e.getMessage());
            }
        }
        return status;
    }

    @PostMapping(value = "/upload")
    @ResponseBody
    public String upload(@RequestParam("myfile") MultipartFile file, Model model) {
        String status = "{\"status\" : \"200\", \"msg\" : \"sucess\"}";
        try {
            ExcelListener listener = new ExcelListener();
            EasyExcel.read(file.getInputStream(), ExcelDevices.class, listener).sheet(0).doRead();
        } catch (IOException e) {
            status = "{\"status\" : \"100\", \"msg\" : \"failed\"}";
            LOG.error("导入出错：{}", e.getMessage());
        }
        return status;
    }

    @RequestMapping(value="/getDevices", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getDevices(@RequestParam(value="current", required=true, defaultValue="1") Integer current,
                           @RequestParam(value="rowCount", required=true, defaultValue="10") Integer rowCount,
                           @RequestParam(value="searchPhrase", required=false) String searchPhrase,
                           @RequestParam(value="sortId", required=false) String sortId,
                           @RequestParam(value="sortTvname", required=false) String sortTvname,
                           @RequestParam(value="sortTvmodelnumber", required=false) String sortTvmodelnumber,
                           @RequestParam(value="sortTvserialnumber", required=false) String sortTvserialnumber,
                           @RequestParam(value="sortTvroomid", required=false) String sortTvroomid,
                           @RequestParam(value="sortTvmacaddress", required=false) String sortTvmacaddress,
                           @RequestParam(value="sortTvipaddress", required=false) String sortTvipaddress) {
        Map<String, Object> mapPara;
        mapPara = CommonUtils.generatePara(current, rowCount, searchPhrase, CommonVariable.MYSQL);

        if (sortId != null) {
            mapPara.put("sortId", sortId);
        }

        if (sortTvname != null) {
            mapPara.put("sortTvname", sortTvname);
        }

        if (sortTvmodelnumber != null) {
            mapPara.put("sortTvmodelnumber", sortTvmodelnumber);
        }

        if (sortTvserialnumber != null) {
            mapPara.put("sortTvserialnumber", sortTvserialnumber);
        }

        if (sortTvroomid != null) {
            mapPara.put("sortTvroomid", sortTvroomid);
        }

        if (sortTvmacaddress != null) {
            mapPara.put("sortTvmacaddress", sortTvmacaddress);
        }

        if (sortTvipaddress != null) {
            mapPara.put("sortTvipaddress", sortTvipaddress);
        }

        int total;
        total = this.devicesService.selectAllCount();
        List<Map<String, Object>> lists = this.devicesService.selectDevicesWithBootGrid(mapPara);
        return CommonUtils.generateData(lists, current, rowCount, total);
    }

}
