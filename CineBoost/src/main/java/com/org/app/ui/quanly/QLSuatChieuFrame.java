/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.ui.quanly;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.org.app.controller.PhimDao;
import com.org.app.controller.PhongChieuDao;
import com.org.app.controller.SuatChieuDao;
import com.org.app.entity.NhanVien;
import com.org.app.entity.Phim;
import com.org.app.entity.PhongChieu;
import com.org.app.entity.SuatChieu;
import com.org.app.helper.Authenticator;
import com.org.app.helper.DateHelper;
import com.org.app.helper.MessageHelper;
import com.org.app.helper.TimeHelper;
import com.org.app.ui.component.SuatChieuItem;
import com.org.app.util.ColorAndIconBank;
import com.org.app.util.LockDateChooser;
import com.org.app.util.MouseHoverEffect;
import com.org.app.util.ScaleImageIconGenerator;
import com.org.app.util.SubFrame;
import com.org.app.util.SubPanelCreator;
import com.org.app.util.SubPanelCreatorInterfaces;
import com.org.app.util.TableRendererUtil;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author intfs
 */
public class QLSuatChieuFrame extends javax.swing.JFrame implements SubFrame<QLSuatChieuFrame>,SubPanelCreatorInterfaces<SubFrame>{
    SubPanelCreator<QLSuatChieuFrame> subPanel;
    private SuatChieuDao scdao;
    private PhimDao phimdao;
    private PhongChieuDao phongdao;
    private List<SuatChieu> scList;
    private DefaultTableModel model;
    private LinkedHashMap<SuatChieu, Phim> phimSuatMap;
    private LinkedHashMap<String, Phim> phimMap;
    private LinkedHashMap<String, PhongChieu> phongMap;
    private HashMap<String, Integer> luongVeMap;
    private List<SuatChieuItem> scItemList;
    private GroupLayout layout;
    private GroupLayout.ParallelGroup parallel;
    private GroupLayout.SequentialGroup sequential;
    private int index;
    private int index_phong;
    private int index_phim = -1;
    
    private String BTN_SUA_NAME = "Sửa";
    private String BTN_XOA_NAME = "Xóa";
    private String BTN_THEM_NAME = "Thêm";
    private String BTN_MOI_NAME = "Mới";
    
    private String BTN_SUA_ICON = "icons8_refresh_24px_1.png";
    private String BTN_SUA_ICON_ROLL = "icons8_refresh_24px_2.png";
    private String BTN_XOA_ICON = "icons8_remove_24px_1.png";
    private String BTN_XOA_ICON_ROLL = "icons8_remove_24px_2.png";
    private String BTN_THEM_ICON = "icons8_add_24px_1.png";
    private String BTN_THEM_ICON_ROLL = "icons8_add_24px.png";
    private String BTN_MOI_ICON = "icons8_new_copy_24px_1.png";
    private String BTN_MOI_ICON_ROLL = "icons8_new_copy_24px_2.png";
    
    public static String CARD_NAME_MAIN = "suatchieu";
    /**
     * Creates new form QLSuatChieuFrame
     */
    public QLSuatChieuFrame() {
        FlatIntelliJLaf.setup();
        initComponents();
        subPanel = createSubPanel(this);
        subPanel.resize(WIDTH, HEIGHT);
        scdao = new SuatChieuDao();
        phimdao = new PhimDao();
        phongdao = new PhongChieuDao();
        scList = new ArrayList<>();
        scItemList = new ArrayList<>();
        phimSuatMap = new LinkedHashMap<>();
        phimMap = new LinkedHashMap<>();
        phongMap = new LinkedHashMap<>();
        luongVeMap = new HashMap<>();
//        model = (DefaultTableModel) tblSuatChieu.getModel();
        index = -1;
        index_phong = 0;
        jPanel4.setVisible(false);
         setUp();
//        redenerFrame();
    }
    
    
    public QLSuatChieuFrame(Date ngay) {
        this();
    }
    
    
     @Override
    public JPanel getContentPanelFor(JComponent panel) {
        return subPanel.createPanelFor(panel);
    }

    @Override
    public SubPanelCreator createSubPanel(SubFrame f) {
        return new SubPanelCreator<>(f) {
            @Override
            public void render() {
                redenerFrame();
            }
        };
    }
    
    public void redenerFrame(){
        try {
        ExecutorService serverice = Executors.newFixedThreadPool(4);
            List<Callable<Object>> calls = new ArrayList<>();
            calls.add((new Callable<Object>(){
                @Override
                public Object call() throws Exception {
                    loadMapPhim();
                    loadLuongVeMap();
                    loadMapPhong();
                    return null;
                }
            }));
            serverice.invokeAll(calls);
            dcNgayFilter.setDate(DateHelper.now());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void loadLuongVeMap() throws Exception {
        luongVeMap.clear();
        luongVeMap.putAll(scdao.selectLuongVeOfSuat());
    }
    
    public void setUp() {
        LockDateChooser.lock(dcNgay, dcNgayFilter);
        MouseHoverEffect.mouseOverEffectButton(ColorAndIconBank.ROLLOVER_BUTTON, btnFirst,btnLast, btnNext, btnPre);
        MouseHoverEffect.mouseOverEffectButton(ColorAndIconBank.ROLLOVER_BUTTON, btnSua,btnXoa);        
        btnChangeState.setUI(new MetalToggleButtonUI() {
                             @Override
                protected Color getSelectColor() {
                return Color.decode("#B3CECC");
                
        }
        });
//        dcNgayFilter.setDate(DateHelper.now());
//    setButtonState(isUpdateMode());
    }
    
     private void buildPanelLichChieuLayout() {
//        render timeline ui
        pnlSuatChieu.removeAll();
        layout = (GroupLayout) pnlSuatChieu.getLayout();
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        parallel = layout.createParallelGroup();
        layout.setHorizontalGroup(layout.createSequentialGroup().addGroup(parallel));
        sequential = layout.createSequentialGroup();
        layout.setVerticalGroup(sequential);
    }

    @Override
    public SubPanelCreator getSubPanelCreator() {
        return this.subPanel;
    }
    
    private void loadSuatChieuTbl() {
        try {
            buildPanelLichChieuLayout();
            scList.clear();
            scItemList.clear();
            
            if(isNgayFilterOn()){
//                System.out.println("fitler day = "+ dcNgayFilter.getDate().toString());
                scList = scdao.selectByDateOnly(DateHelper.toDate(dcNgayFilter.getDate()));
            }
            else scList = scdao.selectAll();
            
            handleEmptyList();
            loadMapPhimSuat(scList);
            setSuatChieuItemList(scList);
            changeFormButtonState();
            
            if(scList.size() != 0 && index == -1) index = 0; setSelectedRow();
            cboPhongFilter.setSelectedIndex(index_phong);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
        
    private void setSuatChieuItemClickListener(SuatChieuItem item) {
      item.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                suatChieuItemClickAction(item);
            }
        });
    }
    
    private void suatChieuItemClickAction(SuatChieuItem suatChieuItem) {
        Component[] components = pnlSuatChieu.getComponents();
        for (Component component : components) {
            if (component instanceof SuatChieuItem) {
                ((SuatChieuItem) component).setItemUnSelected();
            }
        }
        index = suatChieuItem.getSuatChieuIndex();
        suatChieuItem.setItemSelected();
        
        
        SuatChieu selectedItem = suatChieuItem.getSuatChieu();
        setForm(suatChieuItem);
        updateControlState();

        LocalDateTime dtsuat = DateHelper.toLocalDateTime(selectedItem.getNgayChieu(), selectedItem.getGioBatDau());
        if (isUpdateMode() && DateHelper.compare(dtsuat, LocalDateTime.now()) <= 0 ||  (isUpdateMode() && suatChieuItem.getLuongVe() > 0))
        {
            boolean isHaveVe = suatChieuItem.getLuongVe() > 0;
            btnSua.setEnabled(!isHaveVe);
            btnXoa.setEnabled(!isHaveVe);
            
            System.out.println("\t s");
            btnSua.setEnabled(false);
        } else {
            btnSua.setEnabled(true);
            btnXoa.setEnabled(true);
        }
        pnlSuatChieu.scrollRectToVisible(suatChieuItem.getBounds());
    }
    
    private void handleEmptyList() {
       if(phimMap.isEmpty()) {
           lblGioKetThuc.setText("");
           lblThoiLuong.setText("");
           
       }
   }
    
    private void loadSuatChieuTbl(String id_phong) {
        try {
            buildPanelLichChieuLayout();
            scList.clear();

            Date ngay = DateHelper.toDate(dcNgay.getDate());
            if(isNgayFilterOn()) {
                ngay = DateHelper.toDate(dcNgayFilter.getDate());
            }
            scList = scdao.selectByNgayPhong(ngay, id_phong);
            System.out.println("scList "+ scList );
           
            ExecutorService serverice = Executors.newFixedThreadPool(4);
            List<Callable<Object>> calls = new ArrayList<>();
            calls.add((new Callable<Object>(){
                @Override
                public Object call() throws Exception {
                    loadMapPhimSuat(scList);
                    setSuatChieuItemList(scList);
                    changeFormButtonState();
                    return null;
                }
            }));
            serverice.invokeAll(calls);
            if(scList.size() != 0 && index == -1) index = 0; setSelectedRow();
            cboPhongFilter.setSelectedIndex(index_phong);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadListTo() {
        for(SuatChieu sc : scList ) {
                Object ngayGio = DateHelper.toString(sc.getNgayChieu(), DateHelper.DDMMYYY_SLASH_FORMAT) + " " + TimeHelper.toString(sc.getGioBatDau());
                model.addRow(new Object[] {sc.getId(), sc.getPhong(), getPhimById(sc.getPhim()).toString(), ngayGio});
        }
    }
      
    private Phim getPhimById(String id) {
        return phimMap.get(id);
    }
    
    private void loadMapPhim() throws Exception {
        phimMap.clear();
        List<Phim> phims = phimdao.selectAllAvailable();
        Map<String, Phim> map = phims.stream().collect(toMap(Phim::getId, Function.identity()));
        phimMap.putAll(map);
        System.out.println("phimMap = "+phimMap.size());
        
        loadPhimCombo();
    }
        
    private void loadPhimCombo() {
        DefaultComboBoxModel m = (DefaultComboBoxModel) cboPhim.getModel();
        m.removeAllElements();

        cboPhim.removeAllItems();
        for(Phim p : phimMap.values()) {
            cboPhim.addItem(p);
        }
    }
    

    private void loadMapPhong() throws Exception {
        phongMap.clear();
        Map<String, PhongChieu> map = phongdao.selectToMap(phongdao.selectAll());
        phongMap.putAll(map);
       loadPhongFilterCombo();
    }
    
    private void loadPhongCombo() {
        cboPhongChieu.removeAllItems();
        for(PhongChieu pc : phongMap.values()) {
            cboPhongChieu.addItem(pc);
        }
    }
    
        private void loadPhongFilterCombo() {
        System.out.println("load loadPhongFilterCombo");
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboPhongFilter.getModel();
        

        model.removeAllElements();
        model.addElement("Tất cả");
        for(PhongChieu pc : phongMap.values()) {
            model.addElement("Phòng "+pc.toString()+"");
        }
            System.out.println("cbophong count "+ cboPhongFilter.getItemCount());
        loadPhongCombo();
        cboPhongFilter.setSelectedIndex(0);
        
    }
    private void changeFormButtonState() {
        boolean noSuat = phimSuatMap.isEmpty();
        boolean isUpdate = btnChangeState.isSelected();
        System.out.println("!noSuat " + !noSuat);
        dcNgay.setEnabled( (!noSuat) || (noSuat && !isUpdate));
        
        btnSua.setEnabled((!noSuat) || (noSuat && !isUpdate));
        btnXoa.setEnabled((!noSuat) || (noSuat && !isUpdate));
        cboPhim.setEnabled((!noSuat) || (noSuat && !isUpdate));
        cboPhongChieu.setEnabled((!noSuat) || (noSuat && !isUpdate));
        spnHour.setEnabled((!noSuat) || (noSuat && !isUpdate));
        spnMinute.setEnabled((!noSuat) || (noSuat && !isUpdate));
        if(noSuat && isUpdate) {
            lblGioKetThuc.setForeground(Color.GRAY);
            lblThoiLuong.setForeground(Color.GRAY);
        }
        updateControlState();
    }
    
    private void updateControlState() {
        boolean first = index <= 0;
        boolean last = index == scList.size()-1;
        boolean noSuat = phimSuatMap.isEmpty();
        
        btnFirst.setEnabled(!first);
        btnLast.setEnabled(!last);
        btnPre.setEnabled(!first);
        btnNext.setEnabled(!last);
        
        if(noSuat) {
            btnFirst.setEnabled(false);
            btnLast.setEnabled(false);
            btnPre.setEnabled(false);
            btnNext.setEnabled(false);
        }
    }
    
    private void loadMapPhimSuat(List<SuatChieu> list) {
        phimSuatMap.clear();
        for(SuatChieu sc : list) {
            phimSuatMap.put(sc, getPhimById(sc.getPhim()));
            
        }
        
    }
    
    private void setButtonState(boolean selected) {
        String btnStateText = "Thêm suất chiếu";
        String btnSuaText = BTN_SUA_NAME;
        String btnSuaIcon = BTN_SUA_ICON;
        String btnSuaIconRoll = BTN_SUA_ICON_ROLL;
        
        String btnXoaText = BTN_XOA_NAME;
        String btnXoaIcon = BTN_XOA_ICON;
        String btnXoaIconRoll = BTN_XOA_ICON_ROLL;

        lblGioKetThuc.setForeground(Color.BLACK);
        lblThoiLuong.setForeground(Color.BLACK);
        if(!selected) {
            btnSuaText = BTN_THEM_NAME;
            btnSuaIcon = BTN_THEM_ICON;
            btnSuaIconRoll = BTN_THEM_ICON_ROLL;
            
            btnXoaText = BTN_MOI_NAME;
            btnXoaIcon = BTN_MOI_ICON;
            btnXoaIconRoll = BTN_MOI_ICON_ROLL;
            btnStateText = "Cập nhật suất chiếu";
            

        }
        
        btnChangeState.setText(btnStateText);
        btnSua.setText(btnSuaText);
        btnSua.setIcon(ScaleImageIconGenerator.getImageFromResources(btnSuaIcon));
        btnSua.setRolloverIcon(ScaleImageIconGenerator.getImageFromResources(btnSuaIconRoll));
        
        btnXoa.setText(btnXoaText);
        btnXoa.setIcon(ScaleImageIconGenerator.getImageFromResources(btnXoaIcon));
        btnXoa.setRolloverIcon(ScaleImageIconGenerator.getImageFromResources(btnXoaIconRoll));
        
        
        
       if(isUpdateMode() && !scList.isEmpty()) {
           index = 0;
           setSelectedRow();
           return;
       }else {
           clear();
       }
        changeFormButtonState();
    }
    
    
     private void setForm(SuatChieuItem selected) {
        dcNgay.setDate(selected.getNgayChieu());
        cboPhim.setSelectedItem(selected.getPhim());
        cboPhongChieu.setSelectedItem(phongMap.get(selected.getPhong()));
        
        
        setGioSpinner(selected.getGioBatDau());
        setThoiLuong(selected.getPhim().getId());
        setGioKetThuc(selected.getGioKetThuc());
        
    }
    
    private void setThoiLuong(String idPhim) {
        Phim p = getPhimById(idPhim);
        String tl = TimeHelper.convertMinuteToTimeFormat(p.getThoiLuong());
        lblThoiLuong.setText(tl);
        updateGioKetThuc();
    }
    
    private void setGioKetThuc(Time time) {
        
        lblGioKetThuc.setText(TimeHelper.toString(time, true));
    }
    
    private void updateGioKetThuc(){
        lblGioKetThuc.setText(TimeHelper.toString(getGioKetThuc(), true));
    }
    
    private Time getGioKetThuc() {
        Integer h = (Integer) spnHour.getValue();
        Integer m = (Integer) spnMinute.getValue();
        
        int thoiLuong = getSelectedPhim().getThoiLuong();
        
        Time gbd = getGioBatDau();
        Time gkt = TimeHelper.add(gbd, thoiLuong);
        
        
        return gkt;
    }
    
    
    
    private void setGioSpinner(Time time) {
        spnHour.setValue(TimeHelper.getHourtoString(time));
        spnMinute.setValue(TimeHelper.getMinutetoString(time));
    }
    
    private void setSelectedRow() {
//        System.out.println("index = "+ index);
        if( index == -1 || index > scItemList.size()-1) {
            index = -1;
            return;
        }
        System.out.println("index = " + index);
        suatChieuItemClickAction(scItemList.get(index));        
        
    }
    
    public void setSelecteSuat(SuatChieu sc) {
        int i = scList.indexOf(sc);
        if(i == -1) return;
        index = i;
        setSelectedRow();
    }
    
    private SuatChieu getForm() {
        SuatChieu sc = new SuatChieu();
        
        if(index != -1 && isUpdateMode()) sc.setId((String)getSelectedSuatChieuItem().getIdSuat());
                
        sc.setNgayChieu(DateHelper.toDate(dcNgay.getDate()));
        sc.setPhong(((PhongChieu)cboPhongChieu.getSelectedItem()).getId());
        sc.setGioBatDau(getGioBatDau());
        sc.setPhim(getSelectedPhim().getId());
        sc.setGioKetThuc(getGioKetThuc());
        sc.setNv(Authenticator.USER.getId());
        System.out.println("SC from GetForm "+sc.toFullString());
        return sc;
    }
    
    
    
    private String getGioKetThucText() {
        return lblGioKetThuc.getText();
    }
    
    private Time getGioBatDau() {
        Integer h = (Integer) spnHour.getValue();
        Integer m = (Integer) spnMinute.getValue();
        
        return TimeHelper.toTime(h,m);
    }
    
    
    private Phim getSelectedPhim() {
        return (Phim) cboPhim.getSelectedItem();
    }
   
    
    private void update(){
        SuatChieu oldSC = getSelectedSuatChieuItem().getSuatChieu();
        SuatChieuItem selectedItem = getSelectedSuatChieuItem();
        try {
            SuatChieu sc = getForm();
            if(checkUpdateSuat(sc)) {
                scdao.update(sc);
                MessageHelper.message(this, "Cập nhật thành công suất chiếu",ColorAndIconBank.Icon.UPDATE);
                if(!oldSC.getPhong().equals(sc.getPhong())) cboPhongFilter.setSelectedIndex(getFilterIndexOf(sc.getPhong()));
                
                if(!sc.getNgayChieu().equals(oldSC.getNgayChieu()) && isNgayFilterOn()) {
                    dcNgayFilter.setDate(sc.getNgayChieu());
                }
                setSelectedFilter();
                index = scItemList.indexOf(selectedItem);
                setSelectedRow();
            }
        } catch (Exception ex) {
            MessageHelper.message(this, ex.getMessage(),0);
        }
    }
    
   
    
    private boolean checkUpdateSuat(SuatChieu sc) throws Exception {
        Time newGbd = sc.getGioBatDau();
        Time newGkt = sc.getGioKetThuc();
        String pc = sc.getPhong();
        Date d = sc.getNgayChieu();
        System.out.println("newGbd = "+ newGbd.toString() );
        System.out.println("newGkt = "+ newGkt.toString() );
        String mess = "";
        LocalDateTime scGio = DateHelper.toLocalDateTime(d, newGbd);
        
        boolean add = true;
        if(newGbd.toLocalTime().isAfter(LocalTime.of(23, 30))) {
            MessageHelper.message(this,"Lỗi" ,"Suất chiếu không thể sau 23:30.", MessageHelper.ERROR_MESSAGE);
            return false;
        }
        if(DateHelper.compare(scGio, LocalDateTime.now()) <= 0) {
            add =  MessageHelper.confirm(this, "Xác nhận","Suất chiếu đã qua giờ, bạn có muốn thêm suất?");
        } 
        if(!add) return false;
        
        List<SuatChieu> suatChieuList = scdao.selectSuatChieuTrongKhoang(d, pc, newGkt, newGkt);
        System.out.println("Loc suatChieuList: "+ suatChieuList.size()+"isUpdateMode() "+ isUpdateMode());
        if(suatChieuList.size() == 0) return true;
        
        for(SuatChieu s : suatChieuList) {
            if(TimeHelper.compare(newGbd, s.getGioBatDau()) == 0
                    & TimeHelper.compare(newGkt, s.getGioKetThuc()) == 0) {
                if(isUpdateMode()) return true;
                else {
                    mess = String.format("Giờ chiếu trùng với suất chiếu %s(%s-%s)",
                                            s.getId(), TimeHelper.toStringWithHourMintues(sc.getGioBatDau()),
                                            TimeHelper.toStringWithHourMintues(sc.getGioKetThuc()) );
                MessageHelper.message(this, mess,0);
                return false;
                }
            }else if(TimeHelper.compare(newGbd, s.getGioBatDau()) > 0 && TimeHelper.compare(newGbd, s.getGioKetThuc()) < 0) {
                if(s.getId().equals(sc.getId())) return true;
                mess = String.format("Giờ bắt đầu trùng với suất chiếu %s(%s-%s)",
                                            s.getId(), TimeHelper.toStringWithHourMintues(sc.getGioBatDau()),
                                            TimeHelper.toStringWithHourMintues(sc.getGioKetThuc()) );
                MessageHelper.message(this, mess,0);
                return false;
            }else if(TimeHelper.compare(newGkt, s.getGioBatDau()) > 0 && TimeHelper.compare(newGkt, s.getGioKetThuc()) < 0 ) {
                 if(s.getId().equals(sc.getId())) return true;
                mess = String.format("Giờ kết thúc trùng với suất chiếu %s(%s-%s)",
                                            s.getId(), TimeHelper.toStringWithHourMintues(sc.getGioBatDau()),
                                            TimeHelper.toStringWithHourMintues(sc.getGioKetThuc()) );
                MessageHelper.message(this, mess,0);
                return false;
            }
        }

        return true;
    } 

    private void insert() {
        try {
            SuatChieu sc = getForm();
            if(checkUpdateSuat(sc)) {
                scdao.insert(sc);
                MessageHelper.message(this, "Thêm suất chiếu thành công",ColorAndIconBank.Icon.UPDATE);
                setFilterIndex();
                System.out.println("filter inder "+ cboPhongFilter.getSelectedIndex());
                setSelectedFilter();
                index = findMatchSC(sc);
                System.out.println("index after find  = "+ index);
                setSelectedRow();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            MessageHelper.message(this, ex.getMessage(),0);
        }   
    }
    
    private int findMatchSC(SuatChieu sc) {
        SuatChieu s = (SuatChieu) scItemList.stream().map(
                e -> e.getSuatChieu()).filter(e -> (
                        
                    e.getPhim().equals(sc.getPhim())
                    && (TimeHelper.compare(e.getGioBatDau(), sc.getGioBatDau()) == 0 )
                    && (TimeHelper.compare(e.getGioKetThuc(), sc.getGioKetThuc()) == 0 )
                    && (e.getNgayChieu().compareTo(sc.getNgayChieu()) == 0)
                    && (e.getPhong().equals(sc.getPhong()))
                    && (e.getNv().equals(sc.getNv()))
                                )
        ).toArray()[0];
        for (int i = 0; i < scItemList.size(); i++) {
            if(scItemList.get(i).getSuatChieu().getId().equals(s.getId())) {
                return i;
        }
            System.out.println("not foound in for");
        
    }
        return scItemList.indexOf(s);
    }
    
    private void setFilterIndex() {
        if(cboPhongFilter.getSelectedIndex() != 0) {
            PhongChieu p = (PhongChieu)cboPhongChieu.getSelectedItem();
            cboPhongFilter.setSelectedIndex(getFilterIndexOf(p.toString()));
        }
    }
    
    private int getFilterIndexOf(String id) {
        String f = "";
        String id_f = "";
        System.out.println("id_phong_cbo = "+id);
        for (int i = 0; i < cboPhongFilter.getItemCount(); i++) {
            id_f = getStringIDPhongFromFilter(i);
            System.out.println("id_f = "+ f+"\n id = "+id);
            if(id_f.equals(id)) {
                cboPhongFilter.setSelectedIndex(i);
                return i;
            }
        }
        return 0;
    }
    
    private void delete() {
        if(index == -1) return;
        SuatChieu sc = scList.get(index);
        if(MessageHelper.confirm(this,String.format("Bạn có muốn xóa suất chiếu %s?",sc.getId()))) {
            try {
                MessageHelper.message(this, "Xóa thành công.", ColorAndIconBank.Icon.DELETE);
                scdao.delete(sc.getId());
                setSelectedFilter();
                if(index == scList.size() -1) index--;
                setSelectedRow();
            } catch (Exception ex) {
                ex.printStackTrace();
                 MessageHelper.message(this, ex.getMessage(),0);
            }
        }
    
    }
    
     private void clear() {
           Component[] components = pnlSuatChieu.getComponents();
            for (Component component : components) {
            if (component instanceof SuatChieuItem) {
                ((SuatChieuItem) component).setItemUnSelected();
            }
        }
        index = -1;
        if(isNgayFilterOn()) dcNgay.setDate(dcNgayFilter.getDate());
        

        if(cboPhongFilter.getSelectedIndex() != 0) {
            cboPhongChieu.setSelectedIndex(index_phong - 1);
        }
       
       spnHour.setValue(7);
       spnMinute.setValue(0);
       if(cboPhongFilter.getSelectedIndex() == 0) cboPhongChieu.setSelectedIndex(0);
        setThoiLuong(getSelectedPhim().getId());
        
    }
     
    private String getStringIDPhongFromFilter() {
       return getStringIDPhongFromFilter(index_phong);
    }
    
    private String getStringIDPhongFromFilter(int index) {
         String id_phong = (String) cboPhongFilter.getItemAt(index);
        if(id_phong.equals(cboPhongFilter.getItemAt(0))) return "Tất cả";
        return id_phong = id_phong.replaceAll("(.*\\s{1})(\\d)$","$2");
    }
     
    private void setSelectedFilter() {;
        if(index_phong == -1) return;
         String id_phong  = getStringIDPhongFromFilter();
        if(id_phong.equals(cboPhongFilter.getItemAt(0)) ) {
            loadSuatChieuTbl();
            return;
        }
        SuatChieuItem sci = getSelectedSuatChieuItem();
        SuatChieu current = sci == null? null : sci.getSuatChieu();
        String id_s = current == null? null : current.getId();

        
        id_phong = id_phong.replaceAll("(.*\\s{1})(\\d)$","$2");
        for(String pc : phongMap.keySet()) {
            if(Integer.parseInt(pc.substring(1,pc.length())) == Integer.parseInt(id_phong)) {
                cboPhongChieu.setSelectedItem((PhongChieu)phongMap.get(pc));
                loadSuatChieuTbl(pc);
                if(id_s!=null) {
                    int in = getSuatChieuById(current.getId());
                    index = in;
                }else index = -1;
                if(scList.size() != 0 && index == -1) index = 0; setSelectedRow();
                return;
            }
        }
    }
    
    
    private int getSuatChieuById(String id) {
        for (int i = 0; i < scList.size(); i++) {
            if(scList.get(i).getId().equals(id)) {
                return i;
                
            }
        }
          return -1;  
    }
    
    private SuatChieuItem getSelectedSuatChieuItem() {
        if(index == -1) return null;
        return scItemList.get(index);
    }
    private boolean isUpdateMode() {return btnChangeState.isSelected();}
    private boolean isNgayFilterOn() {return btnNgayFitler.isSelected();}
    private void setDateChooserUnEditable(JDateChooser dc) {
        JTextFieldDateEditor editor = (JTextFieldDateEditor) dc.getDateEditor();
         editor.setEditable(false);
    }
    
    private void setSuatChieuItemList(List<SuatChieu> list) {
        scItemList.clear();
        for (int i = 0; i < list.size(); i++) {
            Phim p = phimSuatMap.get(list.get(i));
            SuatChieuItem item = createSuatChieuItem(list.get(i), p, i);
            item.setLuongVe(getLuongVe(item.getIdSuat()));
            scItemList.add(item);
            addItemToPanelLichChieu(item);
//            System.out.println("added item");
        }
        pnlSuatChieu.revalidate();
        pnlSuatChieu.repaint();
    }
    
    private SuatChieuItem createSuatChieuItem (SuatChieu sc, Phim p, int index) {
                Dimension panelSize = scrollSuatChieu.getSize();
        SuatChieuItem i =  new SuatChieuItem(sc, p, index);
        //            render suatChieuItem
        Dimension itemSize = i.getPreferredSize();
        itemSize.width = panelSize.width;
        i.setMaximumSize(itemSize);

        setSuatChieuItemClickListener(i);
        return i;
    }
    
    private void addItemToPanelLichChieu(Component component) {
        //            Add suatChieuItem to pnlLichChieu
        parallel.addGroup(layout.createSequentialGroup()
                .addComponent(component));
        sequential.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(component));
    }
    
    private Integer getLuongVe(String idSuat) {
        Integer l = luongVeMap.get(idSuat);
        return l == null? 0 : l;
    }
    
    public void setSuatNgay(java.util.Date date) {
//        System.out.println("set suat ngay");
        dcNgayFilter.setDate(date);
        btnNgayFitler.setSelected(true);
        btnNgayFitlerActionPerformed(null);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContent = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        pnlNgay = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        dcNgay = new com.toedter.calendar.JDateChooser();
        pnlPhong = new javax.swing.JLayeredPane();
        jLabel2 = new javax.swing.JLabel();
        cboPhongChieu = new javax.swing.JComboBox<>();
        pnlPhim = new javax.swing.JLayeredPane();
        cboPhim = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        pnlGio = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        spnHour = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        spnMinute = new javax.swing.JSpinner();
        pnlThoiLuong = new javax.swing.JLayeredPane();
        jLabel4 = new javax.swing.JLabel();
        lblThoiLuong = new javax.swing.JLabel();
        pnlGioKetThuc = new javax.swing.JLayeredPane();
        lblGioKetThuc = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pnlControlButton = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPre = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        scrollSuatChieu = new javax.swing.JScrollPane();
        pnlSuatChieu = new javax.swing.JPanel();
        btnChangeState = new javax.swing.JToggleButton();
        cboPhongFilter = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        dcNgayFilter = new com.toedter.calendar.JDateChooser();
        btnNgayFitler = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlContent.setBackground(new java.awt.Color(255, 255, 255));
        pnlContent.setMaximumSize(new java.awt.Dimension(700, 650));
        pnlContent.setPreferredSize(new java.awt.Dimension(700, 650));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153)));
        jPanel2.setMaximumSize(new java.awt.Dimension(353, 434));
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(353, 434));

        jPanel3.setBackground(new java.awt.Color(228, 230, 215));
        jPanel3.setMaximumSize(new java.awt.Dimension(329, 325));
        jPanel3.setPreferredSize(new java.awt.Dimension(329, 325));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });
        jPanel3.setLayout(new java.awt.GridLayout(6, 1, 0, 4));

        pnlNgay.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel1.setText("Ngày chiếu:");

        dcNgay.setDateFormatString("dd/MM/yyyy");
        dcNgay.setMaxSelectableDate(new java.util.Date(1672509714000L));
        dcNgay.setMaximumSize(new java.awt.Dimension(88, 20));
        dcNgay.setMinSelectableDate(new java.util.Date(1483207314000L));
        dcNgay.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                dcNgayInputMethodTextChanged(evt);
            }
        });
        dcNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcNgayPropertyChange(evt);
            }
        });

        pnlNgay.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlNgay.setLayer(dcNgay, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pnlNgayLayout = new javax.swing.GroupLayout(pnlNgay);
        pnlNgay.setLayout(pnlNgayLayout);
        pnlNgayLayout.setHorizontalGroup(
            pnlNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNgayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dcNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlNgayLayout.setVerticalGroup(
            pnlNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlNgayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dcNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.add(pnlNgay);

        jLabel2.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel2.setText("Phòng chiếu:");

        cboPhongChieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPhongChieuActionPerformed(evt);
            }
        });

        pnlPhong.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlPhong.setLayer(cboPhongChieu, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pnlPhongLayout = new javax.swing.GroupLayout(pnlPhong);
        pnlPhong.setLayout(pnlPhongLayout);
        pnlPhongLayout.setHorizontalGroup(
            pnlPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPhongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(cboPhongChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlPhongLayout.setVerticalGroup(
            pnlPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPhongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboPhongChieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.add(pnlPhong);

        cboPhim.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPhimItemStateChanged(evt);
            }
        });
        cboPhim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPhimActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel3.setText("Phim:");

        pnlPhim.setLayer(cboPhim, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlPhim.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pnlPhimLayout = new javax.swing.GroupLayout(pnlPhim);
        pnlPhim.setLayout(pnlPhimLayout);
        pnlPhimLayout.setHorizontalGroup(
            pnlPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPhimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(cboPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        pnlPhimLayout.setVerticalGroup(
            pnlPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPhimLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboPhim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.add(pnlPhim);

        pnlGio.setMaximumSize(new java.awt.Dimension(0, 0));
        pnlGio.setOpaque(false);
        pnlGio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel5.setText("Giờ chiếu:");
        jLabel5.setMaximumSize(new java.awt.Dimension(80, 40));
        jLabel5.setMinimumSize(new java.awt.Dimension(80, 40));
        jLabel5.setPreferredSize(new java.awt.Dimension(80, 40));
        pnlGio.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 80, 40));

        spnHour.setModel(new javax.swing.SpinnerNumberModel(1, 1, 23, 1));
        spnHour.setFocusable(false);
        spnHour.setMaximumSize(new java.awt.Dimension(41, 20));
        spnHour.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnHourStateChanged(evt);
            }
        });
        spnHour.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                spnHourPropertyChange(evt);
            }
        });
        pnlGio.add(spnHour, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 55, 30));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText(":");
        pnlGio.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 14, -1));

        spnMinute.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 5));
        spnMinute.setFocusable(false);
        spnMinute.setMaximumSize(new java.awt.Dimension(55, 30));
        spnMinute.setMinimumSize(new java.awt.Dimension(55, 30));
        spnMinute.setPreferredSize(new java.awt.Dimension(55, 30));
        spnMinute.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnMinuteStateChanged(evt);
            }
        });
        pnlGio.add(spnMinute, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 55, 30));

        jPanel3.add(pnlGio);

        jLabel4.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel4.setText("Thời lượng:");

        lblThoiLuong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblThoiLuong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThoiLuong.setMaximumSize(new java.awt.Dimension(180, 24));
        lblThoiLuong.setPreferredSize(new java.awt.Dimension(180, 24));

        pnlThoiLuong.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlThoiLuong.setLayer(lblThoiLuong, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pnlThoiLuongLayout = new javax.swing.GroupLayout(pnlThoiLuong);
        pnlThoiLuong.setLayout(pnlThoiLuongLayout);
        pnlThoiLuongLayout.setHorizontalGroup(
            pnlThoiLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThoiLuongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lblThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlThoiLuongLayout.setVerticalGroup(
            pnlThoiLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThoiLuongLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThoiLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(13, 13, 13))
        );

        jPanel3.add(pnlThoiLuong);

        lblGioKetThuc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblGioKetThuc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel6.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel6.setText("Giờ kết thúc:");

        pnlGioKetThuc.setLayer(lblGioKetThuc, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlGioKetThuc.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pnlGioKetThucLayout = new javax.swing.GroupLayout(pnlGioKetThuc);
        pnlGioKetThuc.setLayout(pnlGioKetThucLayout);
        pnlGioKetThucLayout.setHorizontalGroup(
            pnlGioKetThucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGioKetThucLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(lblGioKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlGioKetThucLayout.setVerticalGroup(
            pnlGioKetThucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGioKetThucLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlGioKetThucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(lblGioKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.add(pnlGioKetThuc);

        pnlControlButton.setLayout(new java.awt.GridLayout(1, 4, 5, 0));

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_skip_to_start_26px.png"))); // NOI18N
        btnFirst.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFirst.setFocusable(false);
        btnFirst.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_skip_to_start_26px_1.png"))); // NOI18N
        btnFirst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFirstMouseEntered(evt);
            }
        });
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        pnlControlButton.add(btnFirst);

        btnPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_sort_left_26px_5.png"))); // NOI18N
        btnPre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPre.setFocusable(false);
        btnPre.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_sort_left_26px_2.png"))); // NOI18N
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });
        pnlControlButton.add(btnPre);

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_sort_right_26px_2.png"))); // NOI18N
        btnNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNext.setFocusable(false);
        btnNext.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_sort_right_26px.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        pnlControlButton.add(btnNext);

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_end_26px.png"))); // NOI18N
        btnLast.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLast.setFocusable(false);
        btnLast.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_end_26px_2.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        pnlControlButton.add(btnLast);

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_refresh_24px_1.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSua.setFocusable(false);
        btnSua.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_refresh_24px_2.png"))); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_remove_24px_1.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoa.setFocusable(false);
        btnXoa.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_remove_24px_2.png"))); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(pnlControlButton, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(54, 54, 54))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSua, btnXoa});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlControlButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnSua, btnXoa});

        jLabel8.setFont(new java.awt.Font("Corbel", 0, 24)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_planner_48px.png"))); // NOI18N
        jLabel8.setText("Quản lý suất chiếu");
        jLabel8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jPanel5.setMaximumSize(new java.awt.Dimension(314, 400));
        jPanel5.setPreferredSize(new java.awt.Dimension(314, 400));

        scrollSuatChieu.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollSuatChieu.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollSuatChieu.setPreferredSize(new java.awt.Dimension(100, 165));

        pnlSuatChieu.setMinimumSize(new java.awt.Dimension(100, 50));

        javax.swing.GroupLayout pnlSuatChieuLayout = new javax.swing.GroupLayout(pnlSuatChieu);
        pnlSuatChieu.setLayout(pnlSuatChieuLayout);
        pnlSuatChieuLayout.setHorizontalGroup(
            pnlSuatChieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 304, Short.MAX_VALUE)
        );
        pnlSuatChieuLayout.setVerticalGroup(
            pnlSuatChieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );

        scrollSuatChieu.setViewportView(pnlSuatChieu);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollSuatChieu, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollSuatChieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        btnChangeState.setBackground(new java.awt.Color(179, 206, 204));
        btnChangeState.setSelected(true);
        btnChangeState.setText("Thêm lịch chiếu");
        btnChangeState.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnChangeState.setFocusable(false);
        btnChangeState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeStateActionPerformed(evt);
            }
        });

        cboPhongFilter.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPhongFilterItemStateChanged(evt);
            }
        });
        cboPhongFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPhongFilterActionPerformed(evt);
            }
        });

        jPanel1.setMaximumSize(new java.awt.Dimension(362, 42));
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(362, 42));

        jPanel4.setBackground(new java.awt.Color(255, 0, 204));
        jPanel4.setMaximumSize(new java.awt.Dimension(204, 42));
        jPanel4.setOpaque(false);

        dcNgayFilter.setDateFormatString("dd/MM/yyyy");
        dcNgayFilter.setMaximumSize(new java.awt.Dimension(184, 20));
        dcNgayFilter.setPreferredSize(new java.awt.Dimension(184, 20));
        dcNgayFilter.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcNgayFilterPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dcNgayFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dcNgayFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnNgayFitler.setText("Lọc theo ngày");
        btnNgayFitler.setPreferredSize(new java.awt.Dimension(120, 23));
        btnNgayFitler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgayFitlerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNgayFitler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNgayFitler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(cboPhongFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlContentLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlContentLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnChangeState, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(13, 13, 13))
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContentLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPhongFilter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnChangeState))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        getContentPane().add(pnlContent, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFirstMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFirstMouseEntered
     
    }//GEN-LAST:event_btnFirstMouseEntered

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if(isUpdateMode()) update();
        else insert();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if(isUpdateMode()) delete();
        else clear();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnChangeStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeStateActionPerformed
        setButtonState(isUpdateMode());
//        tblSuatChieu.setRowSelectionAllowed(isUpdateMode());
    }//GEN-LAST:event_btnChangeStateActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
       index = 0;
       setSelectedRow();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        if(index > 0) index--;
        setSelectedRow();
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if(index < scList.size()-1) index++;
        setSelectedRow();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        index = scList.size()-1;
        setSelectedRow();
    }//GEN-LAST:event_btnLastActionPerformed

    private void spnHourPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spnHourPropertyChange
        
    }//GEN-LAST:event_spnHourPropertyChange

    private void spnMinuteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnMinuteStateChanged
         updateGioKetThuc();
    }//GEN-LAST:event_spnMinuteStateChanged

    private void spnHourStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnHourStateChanged
       updateGioKetThuc();
    }//GEN-LAST:event_spnHourStateChanged

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        jPanel3.requestFocus();
    }//GEN-LAST:event_jPanel3MouseClicked

    private void cboPhimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPhimActionPerformed
        if(cboPhim.getSelectedIndex() == -1) return;
        setThoiLuong(((Phim)cboPhim.getSelectedItem()).getId());
            
        
    }//GEN-LAST:event_cboPhimActionPerformed

    private void cboPhongFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPhongFilterActionPerformed
//        if(evt.getStateChange() == ItemEvent.SELECTED) {
            index_phong = cboPhongFilter.getSelectedIndex();
            System.out.println("index phong = "+ index_phong);
            setSelectedFilter();
//        }
    }//GEN-LAST:event_cboPhongFilterActionPerformed

    private void dcNgayInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_dcNgayInputMethodTextChanged
     
    }//GEN-LAST:event_dcNgayInputMethodTextChanged

    private void dcNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcNgayPropertyChange
           if(!isUpdateMode()) {
               if(!evt.getNewValue().equals(dcNgayFilter.getDate())) {
                   dcNgayFilter.setDate(dcNgay.getDate());
                   setSelectedFilter();
               }
           }
    }//GEN-LAST:event_dcNgayPropertyChange

    private void btnNgayFitlerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgayFitlerActionPerformed
        if(btnNgayFitler.isSelected()) {
            btnNgayFitler.setText("Hiện tất cả suất chiếu");
            jPanel4.setVisible(true);
            dcNgayFilterPropertyChange(null);
//            System.out.println("dddd + "+ dcNgayFilter.getDateEditor().getDate().toString());
            setSelectedFilter();
        }else {
            btnNgayFitler.setText("Lọc theo ngày");
            jPanel4.setVisible(false);
            setSelectedFilter();
        }
        
    }//GEN-LAST:event_btnNgayFitlerActionPerformed

    private void dcNgayFilterPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcNgayFilterPropertyChange
        // TODO add your handling code here:
        if(isNgayFilterOn()) {
           if(!dcNgayFilter.getDate().equals(dcNgay.getDate())){
           dcNgay.setDate(dcNgayFilter.getDate());
               btnNgayFitlerActionPerformed(null);
           }
       
       };
    }//GEN-LAST:event_dcNgayFilterPropertyChange

    private void cboPhongChieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPhongChieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboPhongChieuActionPerformed

    private void cboPhimItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPhimItemStateChanged
        
    }//GEN-LAST:event_cboPhimItemStateChanged

    private void cboPhongFilterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPhongFilterItemStateChanged
        
    }//GEN-LAST:event_cboPhongFilterItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QLSuatChieuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLSuatChieuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLSuatChieuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLSuatChieuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QLSuatChieuFrame sc = new QLSuatChieuFrame(DateHelper.now());
                sc.redenerFrame();
                sc.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnChangeState;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JToggleButton btnNgayFitler;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<Phim> cboPhim;
    private javax.swing.JComboBox<PhongChieu> cboPhongChieu;
    private javax.swing.JComboBox<String> cboPhongFilter;
    private com.toedter.calendar.JDateChooser dcNgay;
    private com.toedter.calendar.JDateChooser dcNgayFilter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblGioKetThuc;
    private javax.swing.JLabel lblThoiLuong;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlControlButton;
    private javax.swing.JPanel pnlGio;
    private javax.swing.JLayeredPane pnlGioKetThuc;
    private javax.swing.JLayeredPane pnlNgay;
    private javax.swing.JLayeredPane pnlPhim;
    private javax.swing.JLayeredPane pnlPhong;
    private javax.swing.JPanel pnlSuatChieu;
    private javax.swing.JLayeredPane pnlThoiLuong;
    private javax.swing.JScrollPane scrollSuatChieu;
    private javax.swing.JSpinner spnHour;
    private javax.swing.JSpinner spnMinute;
    // End of variables declaration//GEN-END:variables

}
