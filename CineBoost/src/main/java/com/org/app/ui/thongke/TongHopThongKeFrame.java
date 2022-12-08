/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.ui.thongke;

//import com.org.app.frames.*;
import com.org.app.util.DinhDangTienTe;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.org.app.controller.ThongKeDAO;
import com.org.app.helper.DateHelper;
import com.org.app.helper.JDBCHelper;
import com.org.app.helper.SettingIconHelper;
import com.org.app.util.CalendarAndClock;
import com.org.app.util.ColorAndIconBank;
import com.org.app.util.ExcelExporter;
import com.org.app.util.SideMenuButton;
import com.org.app.util.SubFrame;
import com.org.app.util.SubPanelCreator;
import com.org.app.util.SubPanelCreatorInterfaces;
import com.org.app.util.TableRendererUtil;
import com.org.app.customui.GradientPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.PieSeries;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.internal.series.Series;
import org.knowm.xchart.style.PieStyler;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.colors.ChartColor;
import org.knowm.xchart.style.colors.XChartSeriesColors;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;

/**
 *
 * @author lenovo
 */
public class TongHopThongKeFrame extends javax.swing.JFrame implements SubFrame<TongHopThongKeFrame>, SubPanelCreatorInterfaces<SubFrame> {
    public static final String CARD_NAME_MAIN = "thongke";
    
    List<SideMenuButton> menuButtonList;
    SubPanelCreator<TongHopThongKeFrame> subPanel;

    ThongKeDAO tk = new ThongKeDAO();
    int row = 0;
    private final String HIEN_TAT_CA_TEXT = "Hiển thị tất cả danh mục";
    private final String HIEN_THEO_BO_LOC_TEXT = "Hiển thị theo bộ lọc";

    /**
     * Creates new form DialogTraCuuLichChieu
     */
    public TongHopThongKeFrame() {
        FlatIntelliJLaf.setup();
        initComponents();
        menuButtonList = new ArrayList<>();
        setUp();
        renderFrame();
        subPanel = createSubPanel(this);
        colorPanel();
        btnDTTong.setText("<html>Doanh thu<br>tổng</html>");
        btnDTPhim.setText("<html>Doanh thu<br>phim</html>");
        btnDTDoAn.setText("<html>Doanh thu<br>đồ ăn</html>");
        btnDTPhongVe.setText("<html>Doanh thu<br>phòng vé</html>");
        btnDTLuongVe.setText("<html>Lượng vé<br>đã bán</html>");
        btnKhungGio.setText("<html>Thống kê<br>khung giờ</html>");
        setUpMenu();
    }

    void colorPanel() {
        pnl1.setBackground(ColorAndIconBank.FILTER_BAR_BACKGROUND);
        pnl2.setBackground(ColorAndIconBank.FILTER_BAR_BACKGROUND);
        pnl3.setBackground(ColorAndIconBank.FILTER_BAR_BACKGROUND);
        pnl4.setBackground(ColorAndIconBank.FILTER_BAR_BACKGROUND);
        pnl5.setBackground(ColorAndIconBank.FILTER_BAR_BACKGROUND);
        pnl6.setBackground(ColorAndIconBank.FILTER_BAR_BACKGROUND);
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
                renderFrame();
            }
        };
    }
    
    private void setUp(){
        dinhDangBang();
        btnHienThiTatCaDanhMuc_DTTong.setSelected(true);
        btnHienThiTatCaDanhMuc_DTPhim.setSelected(true);
        btnHienThiTatCaDanhMuc_DTLuongVe.setSelected(true);
        this.row = 0;
        buttonStatusOpen_LuongVe();
        buttonStatusOpen_DTTong();
        buttonStatusOpen_Phim();
        buttonStatusClose_DoAn();
        buttonStatusClose_PhongVe();
        buttonStatusClose_KhungGio();
        
        try {
            fillTableDTTong_Thang();
            fillTableLuongVe_TheoThang();
            fillTablePhim_TheoThang();
            fillTableDoAn();
            fillTablePhongVe();
            fillTableTKKhungGio();
        } catch (Exception e) {
            e.printStackTrace();
        }
            
        dcsTheoNgay_KhungGio.setDate(DateHelper.now());
    }
    
    private void renderFrame() {
        try {
            fillBieuDo_DTTong();
            fillBieuDo_DTPhim();
            fillBieuDo_LuongVeDaBan();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
    }

    public void fillTableDTTong() throws Exception{
        DefaultTableModel model = (DefaultTableModel) tblDoanhThuTong.getModel();
        model.setRowCount(0);
        List<Object[]> list = tk.getDoanhThuTong();
        for (Object[] row : list) {
            String doanhthuPV = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[0]);
            String doanhthuDA = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[1]);
            String doanhthuTong = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[2]);
            model.addRow(new Object[]{doanhthuPV, doanhthuDA, doanhthuTong});
        }
    }
    
    public void fillTableDTTong_Ngay() throws Exception{
        DefaultTableModel model = (DefaultTableModel) tblDoanhThuTong.getModel();
        model.setRowCount(0);
        List<Object[]> list = tk.getDoanhThuTong_TheoNgay(DateHelper.toDate(dcsTheoNgay_DTTong.getDate()));
        for (Object[] row : list) {
            String doanhthuPV = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[0]);
            String doanhthuDA = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[1]);
            String doanhthuTong = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[2]);
            model.addRow(new Object[]{doanhthuPV, doanhthuDA, doanhthuTong});
        }
    }
    
    public void fillTableDTTong_Thang() throws Exception{
        DefaultTableModel model = (DefaultTableModel) tblDoanhThuTong.getModel();
        model.setRowCount(0);
        Integer thang = Integer.parseInt(cboThangTheoThang_DTTong.getSelectedItem().toString());
        Integer nam = Integer.parseInt(cboNamTheoThang_DTTong.getSelectedItem().toString());
        List<Object[]> list = tk.getDoanhThuTong_TheoThang(thang, nam);
        for (Object[] row : list) {
            String doanhthuPV = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[0]);
            String doanhthuDA = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[1]);
            String doanhthuTong = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[2]);
            model.addRow(new Object[]{doanhthuPV, doanhthuDA, doanhthuTong});
        }
    }
    
    public void fillTableDTTong_Nam() throws Exception{
        DefaultTableModel model = (DefaultTableModel) tblDoanhThuTong.getModel();
        model.setRowCount(0);
        Integer nam = Integer.parseInt(cboTheoNam_DTTong.getSelectedItem().toString());
        List<Object[]> list = tk.getDoanhThuTong_TheoNam(nam);
        for (Object[] row : list) {
            String doanhthuPV = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[0]);
            String doanhthuDA = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[1]);
            String doanhthuTong = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[2]);
            model.addRow(new Object[]{doanhthuPV, doanhthuDA, doanhthuTong});
        }
    }
    
    void fillTableLuongVe() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblLuongVeDaBan.getModel();
        model.setRowCount(0);
        List<Object[]> list = tk.getLuongVeDB();
        for (Object[] row : list) {
            String ngayBan_String = DateHelper.toString((Date)row[0]);
            model.addRow(new Object[]{ngayBan_String, row[1], row[2]});
        }
    }
    
     void fillTableLuongVe_SapXep() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblLuongVeDaBan.getModel();
        model.setRowCount(0);
        List<Object[]> list = tk.getLuongVeDB_SapXep();
        for (Object[] row : list) {
            String ngayBan_String = DateHelper.toString((Date)row[0]);
            model.addRow(new Object[]{ngayBan_String, row[1], row[2]});
        }
    }


    void fillTableLuongVe_TheoNgay() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblLuongVeDaBan.getModel();
        model.setRowCount(0);
        List<Object[]> list = tk.getLuongVeDB_TheoNgay(DateHelper.toDate(dcsTheoNgay_LuongVeDB.getDate()));
        for (Object[] row : list) {
            String ngayBan_String = DateHelper.toString((Date)row[0]);
            model.addRow(new Object[]{ngayBan_String, row[1], row[2]});
        }
    }

    void fillTableLuongVe_TheoThang() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblLuongVeDaBan.getModel();
        model.setRowCount(0);
        int thang = Integer.parseInt(cboThangTheoThang_Vedaban.getSelectedItem().toString());
        int nam = Integer.parseInt(cboNamTheoThang_Vedaban.getSelectedItem().toString());
        List<Object[]> list = tk.getLuongVeDB_TheoThang(thang, nam);
        for (Object[] row : list) {
            String ngayBan_String = DateHelper.toString((Date)row[0]);
            model.addRow(new Object[]{ngayBan_String, row[1], row[2]});
        }
    }

    void fillTableLuongVe_TheoNam() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblLuongVeDaBan.getModel();
        model.setRowCount(0);
        int nam = Integer.parseInt(cboTheoNam_VeDaBan.getSelectedItem().toString());
        List<Object[]> list = tk.getLuongVeDB_TheoNam(nam);
        for (Object[] row : list) {
            String ngayBan_String = DateHelper.toString((Date)row[0]);
            model.addRow(new Object[]{ngayBan_String, row[1], row[2]});
        }
    }

    void fillTablePhim() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblDTPhim.getModel();
        model.setRowCount(0);
        List<Object[]> list = tk.getDoanhThuPhim();
        for (Object[] row : list) {
            String doanhthuPhim = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[3]);
            model.addRow(new Object[]{row[0], row[1], row[2], doanhthuPhim});
        }
    }
    
      void fillTablePhim_SapXep() throws Exception{
        DefaultTableModel model = (DefaultTableModel) tblDTPhim.getModel();
        model.setRowCount(0);
        List<Object[]> list = tk.getDoanhThuPhim_SapXep();
        for (Object[] row : list) {
            String doanhthuPhim = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[3]);
            model.addRow(new Object[]{row[0], row[1], row[2], doanhthuPhim});
        }
    }
      
        void fillTablePhim_Top() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblDTPhim.getModel();
        model.setRowCount(0);
        List<Object[]> list = tk.getDoanhThuPhim_Top();
        for (Object[] row : list) {
            String doanhthuPhim = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[3]);
            model.addRow(new Object[]{row[0], row[1], row[2], doanhthuPhim});
        }
    }

    void fillTablePhim_TheoNgay() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblDTPhim.getModel();
        model.setRowCount(0);
        List<Object[]> list = tk.getDoanhThuPhim_TheoNgay(DateHelper.toDate(dcsTheoNgay_DTPhim.getDate()));
        for (Object[] row : list) {
            String doanhthuPhim = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[3]);
            model.addRow(new Object[]{row[0], row[1], row[2], doanhthuPhim});
        }
    }
 
    void fillTablePhim_TheoThang() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblDTPhim.getModel();
        model.setRowCount(0);
        int thang = Integer.parseInt(cboThangTheoThang_DTPhim.getSelectedItem().toString());
        int nam = Integer.parseInt(cboNamTheoThang_DTPhim.getSelectedItem().toString());
        List<Object[]> list = tk.getDoanhThuPhim_TheoThang(thang, nam);
        for (Object[] row : list) {
            String doanhthuPhim = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[3]);
            model.addRow(new Object[]{row[0], row[1], row[2], doanhthuPhim});
        }
    }

    void fillTablePhim_TheoNam() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblDTPhim.getModel();
        model.setRowCount(0);
        int nam = Integer.parseInt(cboTheoNam_DTPhim.getSelectedItem().toString());
        List<Object[]> list = tk.getDoanhThuPhim_TheoNam(nam);
        for (Object[] row : list) {
            String doanhthuPhim = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[3]);
            model.addRow(new Object[]{row[0], row[1], row[2], doanhthuPhim});
        }
    }

    void fillTableDoAn() throws Exception{
        DefaultTableModel model = (DefaultTableModel) tblDoanhThuDoAn.getModel();
        model.setRowCount(0);
        List<Object[]> list = tk.getDTDoAN();
        for (Object[] row : list) {
            String doanhthuDA = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[3]);
            model.addRow(new Object[]{row[0], row[1], row[2], doanhthuDA});
        }
    }

    void fillTableDoAn_TheoNgay() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThuDoAn.getModel();
        model.setRowCount(0);
        List<Object[]> list = tk.getDTDoAN_TheoNgay(DateHelper.toDate(dscTheoNgay_DTDoAn.getDate()));
        for (Object[] row : list) {
            String doanhthuDA = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[3]);
            model.addRow(new Object[]{row[0], row[1], row[2], doanhthuDA});
        }
    }

    void fillTableDoAn_TheoThang() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThuDoAn.getModel();
        model.setRowCount(0);
        int thang = Integer.parseInt(cboThangTheoThang_DTDoAn.getSelectedItem().toString());
        int nam = Integer.parseInt(cboNamTheoThang_DTDoAn.getSelectedItem().toString());
        List<Object[]> list = tk.getDTDoAN_TheoThang(thang, nam);
        for (Object[] row : list) {
            String doanhthuDA = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[3]);
            model.addRow(new Object[]{row[0], row[1], row[2], doanhthuDA});
        }
    }

    void fillTableDoAn_TheoNam() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThuDoAn.getModel();
        model.setRowCount(0);
        int nam = Integer.parseInt(cboTheoNam_DTDoAN.getSelectedItem().toString());
        List<Object[]> list = tk.getDTDoAN_TheoNam(nam);
        for (Object[] row : list) {
            String doanhthuDA = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[3]);
            model.addRow(new Object[]{row[0], row[1], row[2], doanhthuDA});
        }
    }

    void fillTableDoAn_TheoTen() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThuDoAn.getModel();
        model.setRowCount(0);
        String ten = cboTheoTenDoAn.getSelectedItem().toString();
        List<Object[]> list = tk.getDTDoAN_TheoTen(ten);
        for (Object[] row : list) {
            String doanhthuDA = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[3]);
            model.addRow(new Object[]{row[0], row[1], row[2], doanhthuDA});
        }
    }

    void fillTableDoAn_TheoSize() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThuDoAn.getModel();
        model.setRowCount(0);
        String size = cboTheoSize.getSelectedItem().toString();
        List<Object[]> list = tk.getDTDoAN_TheoSize(size);
        for (Object[] row : list) {
            String doanhthuDA = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[3]);
            model.addRow(new Object[]{row[0], row[1], row[2], doanhthuDA});
        }
    }
    
      void fillTableDoAn_SapXep() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThuDoAn.getModel();
        model.setRowCount(0);
        List<Object[]> list = tk.getDTDoAN_SapXep();
        for (Object[] row : list) {
            String doanhthuDA = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[3]);
            model.addRow(new Object[]{row[0], row[1], row[2], doanhthuDA});
        }
    }

    void fillTablePhongVe() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblDTPhongVe.getModel();
        model.setRowCount(0);
        List<Object[]> list = tk.getDoanhThuPhongVe();
        for (Object[] row : list) {
            String doanhthuPV = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[3]);
            model.addRow(new Object[]{row[0], row[1], row[2], doanhthuPV});
        }
    }

    void fillTablePhongVe_SapXep() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblDTPhongVe.getModel();
        model.setRowCount(0);
        List<Object[]> list = tk.getDoanhThuPhongVe_SapXep();
        for (Object[] row : list) {
            String doanhthuPV = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[3]);
            model.addRow(new Object[]{row[0], row[1], row[2], doanhthuPV});
        }
    }
    
    void fillTablePhongVe_TheoNgay() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblDTPhongVe.getModel();
        model.setRowCount(0);
        List<Object[]> list = tk.getDoanhThuPhongVe_TheoNgay(DateHelper.toDate(dcsNgay_DTPhongVe.getDate()));
        for (Object[] row : list) {
            String doanhthuPV = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[3]);
            model.addRow(new Object[]{row[0], row[1], row[2], doanhthuPV});
        }
    }

    void fillTablePhongVe_TheoThang() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblDTPhongVe.getModel();
        model.setRowCount(0);
        int thang = Integer.parseInt(cboThangTheoThang_DTPhongVe.getSelectedItem().toString());
        int nam = Integer.parseInt(cboNamTheoThang_DTPhongVe.getSelectedItem().toString());
        List<Object[]> list = tk.getDoanhThuPhongVe_TheoThang(thang, nam);
        for (Object[] row : list) {
            String doanhthuPV = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[3]);
            model.addRow(new Object[]{row[0], row[1], row[2], doanhthuPV});
        }
    }

    void fillTablePhongVe_TheoNam() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblDTPhongVe.getModel();
        model.setRowCount(0);
        int nam = Integer.parseInt(cboTheoNam_DTPhongVe.getSelectedItem().toString());
        List<Object[]> list = tk.getDoanhThuPhongVe_TheoNam(nam);
        for (Object[] row : list) {
            String doanhthuPV = DinhDangTienTe.chuyenThanhTienVN((BigDecimal) row[3]);
            model.addRow(new Object[]{row[0], row[1], row[2], doanhthuPV});
        }
    }
    
    void fillTableTKKhungGio() throws Exception{
        // Khung gio bat dau tu 7:00 -> 23:00
        DefaultTableModel model = (DefaultTableModel) tblThongKeKhungGio.getModel();
        model.setRowCount(0);
        int khoangCachKG = Integer.parseInt((String) cboChonKhungGio.getSelectedItem());
        
        List<Object[]> list = tk.getThongKeKG(khoangCachKG, DateHelper.toDate(dcsTheoNgay_KhungGio.getDate()));
        for (Object[] row : list) {
            model.addRow(row);              
        }
    }

          
    public void buttonStatusOpen_DTTong(){
        rdoTheoNgay_DTTong.setEnabled(true);
        dcsTheoNgay_DTTong.setDate(DateHelper.now());
        rdoTheoThang_DTTong.setEnabled(true);
        rdoTheoNam_DTTong.setEnabled(true);
        dcsTheoNgay_DTTong.setEnabled(false);
        cboTheoNam_DTTong.setEnabled(false);
        cboThangTheoThang_DTTong.setEnabled(true);
        cboNamTheoThang_DTTong.setEnabled(true);
                
        rdoTheoThang_DTTong.setSelected(true);
        cboThangTheoThang_DTTong.setSelectedIndex(3);
        cboNamTheoThang_DTTong.setSelectedIndex(2);
    }
    
    public void buttonStatusClose_DTTong(){
        rdoTheoNgay_DTTong.setSelected(false);
        rdoTheoThang_DTTong.setSelected(false);
        rdoTheoNam_DTTong.setSelected(false);
        rdoTheoNgay_DTTong.setEnabled(false);
        dcsTheoNgay_DTTong.setEnabled(false);
        rdoTheoThang_DTTong.setEnabled(false);
        cboThangTheoThang_DTTong.setEnabled(false);
        cboNamTheoThang_DTTong.setEnabled(false);
        rdoTheoNam_DTTong.setEnabled(false);
        cboTheoNam_DTTong.setEnabled(false);
    }
    
    public void buttonStatusOpen_Phim() {
        rdoTheoNgay_DTPhim.setEnabled(true);
        dcsTheoNgay_DTPhim.setDate(DateHelper.now());
        dcsTheoNgay_DTPhim.setEnabled(false);
        rdoTheoNam_DTPhim.setEnabled(true);

        cboTheoNam_DTPhim.setEnabled(false);
        cboThangTheoThang_DTPhim.setEnabled(true);
        cboNamTheoThang_DTPhim.setEnabled(true);
        
//        rdoTheoThang_DTPhim.setEnabled(true);
        rdoTheoThang_DTPhim.setSelected(true);
        cboThangTheoThang_DTPhim.setSelectedIndex(3);
        cboNamTheoThang_DTPhim.setSelectedIndex(2);
    }

    public void buttonStatusClose_Phim() {
        rdoTheoNgay_DTPhim.setSelected(false);
        rdoTheoThang_DTPhim.setSelected(false);
        rdoTheoNam_DTPhim.setSelected(false);
        rdoTheoNgay_DTPhim.setEnabled(false);
        dcsTheoNgay_DTPhim.setEnabled(false);
        rdoTheoThang_DTPhim.setEnabled(false);
        cboThangTheoThang_DTPhim.setEnabled(false);
        cboNamTheoThang_DTPhim.setEnabled(false);
        rdoTheoNam_DTPhim.setEnabled(false);
        cboTheoNam_DTPhim.setEnabled(false);
    }

    public void buttonStatusOpen_DoAn() {
        rdoTheoNgay_DTDoAn.setSelected(true);
        rdoTheoNgay_DTDoAn.setEnabled(true);
        dscTheoNgay_DTDoAn.setEnabled(true);
        dscTheoNgay_DTDoAn.setDate(DateHelper.now());
        rdoTheoThang_DTDoAn.setEnabled(true);
        rdoTheoNam_DTDoAn.setEnabled(true);
        rdoTheoTenDoAn.setEnabled(true);
        rdoTheoSize.setEnabled(true);
    }

    public void buttonStatusClose_DoAn() {
        rdoTheoNgay_DTDoAn.setSelected(false);
        rdoTheoThang_DTDoAn.setSelected(false);
        rdoTheoNam_DTDoAn.setSelected(false);
        rdoTheoTenDoAn.setSelected(false);
        rdoTheoSize.setSelected(false);
        rdoTheoNgay_DTDoAn.setEnabled(false);
        dscTheoNgay_DTDoAn.setEnabled(false);
        rdoTheoThang_DTDoAn.setEnabled(false);
        cboThangTheoThang_DTDoAn.setEnabled(false);
        cboNamTheoThang_DTDoAn.setEnabled(false);
        rdoTheoNam_DTDoAn.setEnabled(false);
        cboTheoNam_DTDoAN.setEnabled(false);
        rdoTheoTenDoAn.setEnabled(false);
        cboTheoTenDoAn.setEnabled(false);
        rdoTheoNam_DTDoAn.setEnabled(false);
        cboTheoSize.setEnabled(false);
    }

    public void buttonStatusOpen_PhongVe() {
        rdoTheoNgay_DTPhongVe.setSelected(true);
        rdoTheoNgay_DTPhongVe.setEnabled(true);
        dcsNgay_DTPhongVe.setEnabled(true);
        dcsNgay_DTPhongVe.setDate(DateHelper.now());
        rdoTheoThang_DTPhongVe.setEnabled(true);
        rdoTheoNam_DTPhongVe.setEnabled(true);
    }

    public void buttonStatusClose_PhongVe() {
        rdoTheoNgay_DTPhongVe.setSelected(false);
        rdoTheoThang_DTPhongVe.setSelected(false);
        rdoTheoNam_DTPhongVe.setSelected(false);
        rdoTheoNgay_DTPhongVe.setEnabled(false);
        dcsNgay_DTPhongVe.setEnabled(false);
        rdoTheoThang_DTPhongVe.setEnabled(false);
        cboThangTheoThang_DTPhongVe.setEnabled(false);
        cboNamTheoThang_DTPhongVe.setEnabled(false);
        rdoTheoNam_DTPhongVe.setEnabled(false);
        cboTheoNam_DTPhongVe.setEnabled(false);
    }

    public void buttonStatusOpen_LuongVe() {
        dcsTheoNgay_LuongVeDB.setEnabled(false);
        dcsTheoNgay_LuongVeDB.setDate(DateHelper.now());
        rdoTheoNgay_LuongVeDB.setEnabled(true);
        rdoTheoThang_VeDaBan.setEnabled(true);
        rdoTheoNam_VeDaBan.setEnabled(true);
        cboTheoNam_VeDaBan.setEnabled(false);
        cboThangTheoThang_Vedaban.setEnabled(true);
        cboNamTheoThang_Vedaban.setEnabled(true);
        
        rdoTheoThang_VeDaBan.setSelected(true);
        cboThangTheoThang_Vedaban.setSelectedIndex(3);
        cboNamTheoThang_Vedaban.setSelectedIndex(2);
    }

    public void buttonStatusClose_LuongVe() {
         rdoTheoNgay_LuongVeDB.setSelected(false);
        rdoTheoThang_VeDaBan.setSelected(false);
        rdoTheoNam_VeDaBan.setSelected(false);
         rdoTheoNgay_LuongVeDB.setEnabled(false);
        dcsTheoNgay_LuongVeDB.setEnabled(false);
        rdoTheoThang_VeDaBan.setEnabled(false);
        cboThangTheoThang_Vedaban.setEnabled(false);
        cboNamTheoThang_Vedaban.setEnabled(false);
        rdoTheoNam_VeDaBan.setEnabled(false);
        cboTheoNam_VeDaBan.setEnabled(false);
    }
    
    public void buttonStatusOpen_KhungGio(){
        dcsTheoNgay_KhungGio.setEnabled(true);
        cboChonKhungGio.setEnabled(true);
    }
    
    public void buttonStatusClose_KhungGio(){
        dcsTheoNgay_KhungGio.setEnabled(false);
        cboChonKhungGio.setEnabled(false);
    }
    
    public void fillBieuDo_DTTong(){
        Integer doanhthuPV = DinhDangTienTe.chuyenChuoiThanhInt((String) tblDoanhThuTong.getValueAt(0, 0));
        Integer doanhthuDA = DinhDangTienTe.chuyenChuoiThanhInt((String) tblDoanhThuTong.getValueAt(0, 1));
        
        org.knowm.xchart.PieChart chart = new PieChartBuilder()
                .width(200)
                .height(200)
                .title("Doanh Thu Tổng")
                .build();
 
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAnnotationType(PieStyler.AnnotationType.Label);
        chart.getStyler().setAnnotationDistance(.82);
        chart.getStyler().setPlotContentSize(.9);
        chart.getStyler().setDefaultSeriesRenderStyle(PieSeries.PieSeriesRenderStyle.Donut);
        chart.getStyler().setSumVisible(true);  
        chart.getStyler().setSumFontSize(12);
        Font font = new Font("Serif", Font.BOLD, 8);
        chart.getStyler().setAnnotationsFont(font);
        chart.getStyler().setAnnotationsFontColor(new Color(0, 0, 0));
        chart.getStyler().setSeriesColors(new Color[]{new Color(0, 205, 0), new Color(255, 215, 0)});       
        chart.getStyler().setChartBackgroundColor(Color.WHITE);
        chart.addSeries("Đồ ăn (" + DinhDangTienTe.chuyenThanhTienVN(doanhthuDA) + ")", doanhthuDA);
        chart.addSeries("Phòng vé (" + DinhDangTienTe.chuyenThanhTienVN(doanhthuPV) + ")", doanhthuPV);
        chart.getStyler().setSumFormat(DinhDangTienTe.chuyenThanhTienVN(doanhthuDA + doanhthuPV));
        
        JPanel pnlc = new XChartPanel(chart);
        pnlc.setOpaque(false);
        pnlBD_DTTong.removeAll();
        pnlBD_DTTong.add(pnlc, BorderLayout.CENTER);
        pnlBD_DTTong.validate();
    }
    
    public void fillBieuDo_DTPhim(){
        if (tblDTPhim.getRowCount() > 0) {

        List<String> xData = new ArrayList<String>();
        List<Integer> yData = new ArrayList<Integer>();

        for (int row = 0; row < tblDTPhim.getRowCount(); row++) {
            String xValue = (String) tblDTPhim.getValueAt(row, 0);
            Integer yValue = DinhDangTienTe.chuyenChuoiThanhInt((String) tblDTPhim.getValueAt(row, 3));
            xData.add(xValue);
            yData.add(yValue);
        }
        
        CategoryChart chart = new CategoryChartBuilder()
                    .width(685)
                    .height(300)
                    .title("Thống Kê Doanh Thu Phim")
                    .xAxisTitle("Tên phim")
                    .yAxisTitle("Doanh thu")
                    .build();
            Color[] sliceColors = new Color[]{Color.decode("#1D95F6")};
            chart.getStyler().setSeriesColors(sliceColors);
            chart.getStyler().setHasAnnotations(true);
            chart.getStyler().setDecimalPattern("##,###,###,###");            
            chart.getStyler().setChartBackgroundColor(Color.WHITE);
            chart.getStyler().setXAxisLabelRotation(10);
            chart.getStyler().setLegendVisible(false);
            chart.addSeries("Doanh thu phim", xData, yData);
           
        XChartPanel pnlc = new XChartPanel<CategoryChart>(chart);
        pnlBD_DTPhim.removeAll();
        pnlBD_DTPhim.add(pnlc, BorderLayout.CENTER);
        pnlBD_DTPhim.validate();
        }else{
            JLabel lbl = new JLabel();
            lbl.setText("Cần có dữ liệu trong bảng Doanh Thu Phim để thống kê");
            lbl.setHorizontalAlignment((int) CENTER_ALIGNMENT);
            pnlBD_DTPhim.removeAll();
            pnlBD_DTPhim.add(lbl);
        }
    }
    
    
    public void fillBieuDo_LuongVeDaBan(){
        if (tblLuongVeDaBan.getRowCount() > 0) {
            
        
        XYChart chart = new XYChartBuilder()
                .width(476)
                .height(200)
                .title("Lượng vé bán theo ngày")
                .xAxisTitle("Ngày trong tuần")
                .yAxisTitle("Số lượng vé")
                .build();

        chart.getStyler().setPlotBackgroundColor(ChartColor.getAWTColor(ChartColor.BLACK));
        chart.getStyler().setPlotGridLinesColor(new Color(255, 255, 255));
        chart.getStyler().setChartBackgroundColor(Color.WHITE);
        chart.getStyler().setChartFontColor(Color.MAGENTA);
        chart.getStyler().setChartTitleBoxBackgroundColor(new Color(0, 0, 0));
        chart.getStyler().setChartTitleBoxVisible(true);
        chart.getStyler().setChartTitleBoxBorderColor(Color.BLACK);
        chart.getStyler().setPlotGridLinesVisible(false);

        chart.getStyler().setAxisTickPadding(10);

        chart.getStyler().setAxisTickMarkLength(5);

        chart.getStyler().setPlotMargin(10);

        chart.getStyler().setChartTitleFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        chart.getStyler().setLegendFont(new Font(Font.SERIF, Font.PLAIN, 8));
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setLegendSeriesLineLength(12);
        chart.getStyler().setAxisTitleFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        chart.getStyler().setAxisTickLabelsFont(new Font(Font.SERIF, Font.PLAIN, 11));
        chart.getStyler().setDatePattern("dd/MM");

        chart.getStyler().setCursorEnabled(true);
        
        List<Date> xData = new ArrayList<Date>();
        List<Integer> yData = new ArrayList<Integer>();
        
        for (int row = 0; row < tblLuongVeDaBan.getRowCount(); row++) {
            Date ngay = DateHelper.toDate((String) tblLuongVeDaBan.getValueAt(row, 0), 0);
            Integer luongVe = (Integer) tblLuongVeDaBan.getValueAt(row, 1);
            xData.add(ngay);
            yData.add(luongVe);
        }

        XYSeries series = chart.addSeries("Số vé đã bán", xData, yData);
        series.setLineColor(XChartSeriesColors.BLUE);
        series.setMarkerColor(Color.ORANGE);
        series.setMarker(SeriesMarkers.CIRCLE);
        series.setLineStyle(SeriesLines.SOLID);
    
        JPanel pnlc = new XChartPanel(chart);
        pnlc.setOpaque(false);
        pnlBD_DTLuongVe.removeAll();
        pnlBD_DTLuongVe.add(pnlc, BorderLayout.CENTER);
        pnlBD_DTLuongVe.validate();
        
        }else{
            JLabel lbl = new JLabel();
            lbl.setText("Cần có dữ liệu trong bảng Lượng Vé Đã Bán để thống kê");
            lbl.setHorizontalAlignment((int) CENTER_ALIGNMENT);
            pnlBD_DTLuongVe.removeAll();
            pnlBD_DTLuongVe.add(lbl);
        }
    }
    
    private void setUpMenu() {
        JComponent sideBtns[] = {btnXemBieuDo, btnDTTong, btnDTPhim, btnDTDoAn, btnDTPhongVe, btnDTLuongVe, btnKhungGio};
        JComponent panels[] = {pnlBD, pnlTong, pnlPhim, pnlDA, pnlPV, pnlLV, pnlKG};
        for (int i = 0; i < sideBtns.length; i++) {
            SideMenuButton sbtn = new SideMenuButton((JLabel) sideBtns[i], panels[i], ColorAndIconBank.SIDEMENU_COLOR_HOVERS, true);
            menuButtonList.add(sbtn);
        }
    }

    private SideMenuButton getSelectedButton(JLabel lbl) {
        for (SideMenuButton btn : menuButtonList) {
            if (btn.getButton().equals(lbl)) {
                return btn;
            }
        }
        return null;
    }

    private void clearButtonSelected(SideMenuButton btn) {
        for (SideMenuButton b : menuButtonList) {
            b.setIsSelected(false);
        }
        btn.setIsSelected(true);;
    }

    public void setClickedListener(MouseEvent evt) {
        SideMenuButton btn = getSelectedButton((JLabel) evt.getSource());
        if (btn.isIsSelected()) {
            return;
        }
        clearButtonSelected(btn);
    }
    
    public void dinhDangBang(){
        JTable[] tblArr = {tblDTPhim, tblDTPhongVe, tblDoanhThuDoAn, tblDoanhThuTong, tblLuongVeDaBan, tblThongKeKhungGio};      
        for (int i = 0; i < tblArr.length; i++) {
            TableRendererUtil tbl = new TableRendererUtil(tblArr[i]);
            tbl.changeHeaderStyle(Color.decode("#C3D688"));
            tbl.setColoumnWidthByPersent(1, 10);
            tbl.setRowHeightByPresent(40);
            tblArr[i].setGridColor(new Color(169,197,80));
            tblArr[i].setFocusable(false);
            tblArr[i].setInheritsPopupMenu(true);
            tblArr[i].setShowGrid(true);
            tblArr[i].setSelectionBackground(new Color(61, 144, 106));
            tblArr[i].setSelectionForeground(new Color(244, 244, 244));
            for (int j = 0; j < tblArr[i].getColumnCount(); j++) {
                tbl.setColumnAlignment(j, (int) CENTER_ALIGNMENT);
            }
        
        }
        
//         chỉnh độ rộng của cột
//        TableRendererUtil tblP = new TableRendererUtil(tblArr[0]);
//        tblP.setColoumnWidthByPersent(2, 40);
//        TableRendererUtil tblPV = new TableRendererUtil(tblArr[1]);
//        tblPV.setColoumnWidthByPersent(0, 10);
//        TableRendererUtil tblDA = new TableRendererUtil(tblArr[2]);
//        tblDA.setColoumnWidthByPersent(1, 10);
//        tblDA.setColoumnWidthByPersent(2, 10);
//
//        TableRendererUtil tblLV = new TableRendererUtil(tblArr[4]);
//        tblLV.setColoumnWidthByPersent(1, 20);
//        tblLV.setColoumnWidthByPersent(2, 20);
    }
    
    @Override
    public SubPanelCreator getSubPanelCreator() {
        return subPanel;
    }
    
    // Khi nhập 100.000 String
    // chuyen qua double 100000.0
    // qua csdl decimal 100000.00 relp "." -> 10000000/1000
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgVeDaBan = new javax.swing.ButtonGroup();
        btgThongKeKhungGio = new javax.swing.ButtonGroup();
        btgPhim = new javax.swing.ButtonGroup();
        btgPhongVe = new javax.swing.ButtonGroup();
        btgLuongVe = new javax.swing.ButtonGroup();
        btgDoAn_TG = new javax.swing.ButtonGroup();
        btgDoAn_SP = new javax.swing.ButtonGroup();
        btgDoanhThuTong = new javax.swing.ButtonGroup();
        pnlMenu = new javax.swing.JPanel();
        lblTongHopThongKe = new javax.swing.JLabel();
        pnlControl = new javax.swing.JPanel();
        pnlBD = new GradientPanel();
        btnXemBieuDo = new javax.swing.JLabel();
        pnlTong = new GradientPanel();
        btnDTTong = new javax.swing.JLabel();
        pnlPhim = new GradientPanel();
        btnDTPhim = new javax.swing.JLabel();
        pnlDA = new GradientPanel();
        btnDTDoAn = new javax.swing.JLabel();
        pnlPV = new GradientPanel();
        btnDTPhongVe = new javax.swing.JLabel();
        pnlLV = new GradientPanel();
        btnDTLuongVe = new javax.swing.JLabel();
        pnlKG = new GradientPanel();
        btnKhungGio = new javax.swing.JLabel();
        tabsTonghopthongke = new javax.swing.JTabbedPane();
        pnlBieuDo = new javax.swing.JPanel();
        pnlBD_DTTong = new javax.swing.JPanel();
        pnlBD_DTLuongVe = new javax.swing.JPanel();
        pnlBD_DTPhim = new javax.swing.JPanel();
        pnlDTTong = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDoanhThuTong = new javax.swing.JTable();
        btnXuatFileExcel_DTTong = new javax.swing.JButton();
        lblXuatFileExcel4 = new javax.swing.JLabel();
        pnl1 = new javax.swing.JPanel();
        rdoTheoThang_DTTong = new javax.swing.JRadioButton();
        cboThangTheoThang_DTTong = new javax.swing.JComboBox<>();
        cboNamTheoThang_DTTong = new javax.swing.JComboBox<>();
        rdoTheoNam_DTTong = new javax.swing.JRadioButton();
        cboTheoNam_DTTong = new javax.swing.JComboBox<>();
        rdoTheoNgay_DTTong = new javax.swing.JRadioButton();
        dcsTheoNgay_DTTong = new com.toedter.calendar.JDateChooser();
        btnHienThiTatCaDanhMuc_DTTong = new javax.swing.JToggleButton();
        btnBieuDoNgay_DTTong = new javax.swing.JButton();
        lblBieuDoNgay3 = new javax.swing.JLabel();
        pnlDTPhim = new javax.swing.JPanel();
        pnl3 = new javax.swing.JPanel();
        rdoTheoThang_DTPhim = new javax.swing.JRadioButton();
        cboThangTheoThang_DTPhim = new javax.swing.JComboBox<>();
        cboNamTheoThang_DTPhim = new javax.swing.JComboBox<>();
        rdoTheoNam_DTPhim = new javax.swing.JRadioButton();
        cboTheoNam_DTPhim = new javax.swing.JComboBox<>();
        rdoTheoNgay_DTPhim = new javax.swing.JRadioButton();
        dcsTheoNgay_DTPhim = new com.toedter.calendar.JDateChooser();
        btnHienThiTatCaDanhMuc_DTPhim = new javax.swing.JToggleButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDTPhim = new javax.swing.JTable();
        btnXuatFileExcel_DTPhim = new javax.swing.JButton();
        lblXuatFileExcel3 = new javax.swing.JLabel();
        btnSLVeGiam = new javax.swing.JButton();
        btnTop5DoanhThu = new javax.swing.JButton();
        btnBieuDoNgay_DTPhim = new javax.swing.JButton();
        lblBieuDoNgay2 = new javax.swing.JLabel();
        pnlDTDoAn = new javax.swing.JPanel();
        pnl6 = new javax.swing.JPanel();
        dscTheoNgay_DTDoAn = new com.toedter.calendar.JDateChooser();
        rdoTheoNgay_DTDoAn = new javax.swing.JRadioButton();
        rdoTheoThang_DTDoAn = new javax.swing.JRadioButton();
        rdoTheoNam_DTDoAn = new javax.swing.JRadioButton();
        cboThangTheoThang_DTDoAn = new javax.swing.JComboBox<>();
        cboNamTheoThang_DTDoAn = new javax.swing.JComboBox<>();
        cboTheoNam_DTDoAN = new javax.swing.JComboBox<>();
        rdoTheoTenDoAn = new javax.swing.JRadioButton();
        rdoTheoSize = new javax.swing.JRadioButton();
        cboTheoTenDoAn = new javax.swing.JComboBox<>();
        cboTheoSize = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnHienThiTatCaDanhMuc_DTDoAN = new javax.swing.JToggleButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblDoanhThuDoAn = new javax.swing.JTable();
        btnXuatFileExcel_DoAnDaBan = new javax.swing.JButton();
        lblXuatFileExcel5 = new javax.swing.JLabel();
        pnlPhongVe = new javax.swing.JPanel();
        pnl2 = new javax.swing.JPanel();
        rdoTheoThang_DTPhongVe = new javax.swing.JRadioButton();
        rdoTheoNam_DTPhongVe = new javax.swing.JRadioButton();
        cboThangTheoThang_DTPhongVe = new javax.swing.JComboBox<>();
        cboNamTheoThang_DTPhongVe = new javax.swing.JComboBox<>();
        cboTheoNam_DTPhongVe = new javax.swing.JComboBox<>();
        rdoTheoNgay_DTPhongVe = new javax.swing.JRadioButton();
        dcsNgay_DTPhongVe = new com.toedter.calendar.JDateChooser();
        btnHienThiTatCaDanhMuc_DTPhongVe = new javax.swing.JToggleButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDTPhongVe = new javax.swing.JTable();
        btnXuatFileExcel_DTPhongVe = new javax.swing.JButton();
        lblXuatFileExcel2 = new javax.swing.JLabel();
        pnlVeTheoNgay = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLuongVeDaBan = new javax.swing.JTable();
        pnl4 = new javax.swing.JPanel();
        rdoTheoThang_VeDaBan = new javax.swing.JRadioButton();
        rdoTheoNam_VeDaBan = new javax.swing.JRadioButton();
        cboThangTheoThang_Vedaban = new javax.swing.JComboBox<>();
        cboNamTheoThang_Vedaban = new javax.swing.JComboBox<>();
        cboTheoNam_VeDaBan = new javax.swing.JComboBox<>();
        btnHienThiTatCaDanhMuc_DTLuongVe = new javax.swing.JToggleButton();
        rdoTheoNgay_LuongVeDB = new javax.swing.JRadioButton();
        dcsTheoNgay_LuongVeDB = new com.toedter.calendar.JDateChooser();
        btnXuatFileExcel_VeDaBan = new javax.swing.JButton();
        lblXuatFileExcel = new javax.swing.JLabel();
        btnBieuDoNgay_LuongVeDB = new javax.swing.JButton();
        lblBieuDoNgay4 = new javax.swing.JLabel();
        pnlKhungGio = new javax.swing.JPanel();
        pnl5 = new javax.swing.JPanel();
        dcsTheoNgay_KhungGio = new com.toedter.calendar.JDateChooser();
        btnHienThiTatCaDanhMuc_KhungGio = new javax.swing.JToggleButton();
        cboChonKhungGio = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblXuatFileExcel1 = new javax.swing.JLabel();
        btnXuatFileExcel_TKKhungGio = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblThongKeKhungGio = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(700, 650));

        pnlMenu.setMaximumSize(new java.awt.Dimension(700, 650));
        pnlMenu.setPreferredSize(new java.awt.Dimension(700, 650));
        pnlMenu.setLayout(null);

        lblTongHopThongKe.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        lblTongHopThongKe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTongHopThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_analytics_48px_1.png"))); // NOI18N
        lblTongHopThongKe.setText("TỔNG HỢP - THỐNG KÊ");
        lblTongHopThongKe.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pnlMenu.add(lblTongHopThongKe);
        lblTongHopThongKe.setBounds(0, 10, 330, 40);

        pnlControl.setBackground(new java.awt.Color(255, 255, 255));
        pnlControl.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlControl.setPreferredSize(new java.awt.Dimension(698, 40));
        pnlControl.setLayout(new java.awt.GridLayout(1, 0, 2, 0));

        pnlBD.setBackground(new java.awt.Color(102, 102, 102));

        btnXemBieuDo.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnXemBieuDo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnXemBieuDo.setText("Xem biểu đồ");
        btnXemBieuDo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXemBieuDo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXemBieuDoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnXemBieuDoMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlBDLayout = new javax.swing.GroupLayout(pnlBD);
        pnlBD.setLayout(pnlBDLayout);
        pnlBDLayout.setHorizontalGroup(
            pnlBDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBDLayout.createSequentialGroup()
                .addComponent(btnXemBieuDo, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pnlBDLayout.setVerticalGroup(
            pnlBDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnXemBieuDo, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        pnlControl.add(pnlBD);

        pnlTong.setBackground(new java.awt.Color(102, 102, 102));

        btnDTTong.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnDTTong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDTTong.setText("Doanh thu tổng");
        btnDTTong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDTTong.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDTTong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDTTongMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnDTTongMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlTongLayout = new javax.swing.GroupLayout(pnlTong);
        pnlTong.setLayout(pnlTongLayout);
        pnlTongLayout.setHorizontalGroup(
            pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDTTong, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
        );
        pnlTongLayout.setVerticalGroup(
            pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDTTong, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        pnlControl.add(pnlTong);

        pnlPhim.setBackground(new java.awt.Color(102, 102, 102));

        btnDTPhim.setBackground(new java.awt.Color(0, 102, 102));
        btnDTPhim.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnDTPhim.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDTPhim.setText("Doanh thu phim");
        btnDTPhim.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDTPhim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDTPhimMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnDTPhimMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlPhimLayout = new javax.swing.GroupLayout(pnlPhim);
        pnlPhim.setLayout(pnlPhimLayout);
        pnlPhimLayout.setHorizontalGroup(
            pnlPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
            .addGroup(pnlPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnDTPhim, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
        );
        pnlPhimLayout.setVerticalGroup(
            pnlPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(pnlPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnDTPhim, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
        );

        pnlControl.add(pnlPhim);

        pnlDA.setBackground(new java.awt.Color(102, 102, 102));

        btnDTDoAn.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnDTDoAn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDTDoAn.setText("Doanh thu đồ ăn");
        btnDTDoAn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDTDoAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDTDoAnMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnDTDoAnMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlDALayout = new javax.swing.GroupLayout(pnlDA);
        pnlDA.setLayout(pnlDALayout);
        pnlDALayout.setHorizontalGroup(
            pnlDALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
            .addGroup(pnlDALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnDTDoAn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDALayout.setVerticalGroup(
            pnlDALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(pnlDALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnDTDoAn, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
        );

        pnlControl.add(pnlDA);

        pnlPV.setBackground(new java.awt.Color(102, 102, 102));

        btnDTPhongVe.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnDTPhongVe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDTPhongVe.setText("Phòng vé");
        btnDTPhongVe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDTPhongVe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDTPhongVeMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnDTPhongVeMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlPVLayout = new javax.swing.GroupLayout(pnlPV);
        pnlPV.setLayout(pnlPVLayout);
        pnlPVLayout.setHorizontalGroup(
            pnlPVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
            .addGroup(pnlPVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnDTPhongVe, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
        );
        pnlPVLayout.setVerticalGroup(
            pnlPVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(pnlPVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnDTPhongVe, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
        );

        pnlControl.add(pnlPV);

        pnlLV.setBackground(new java.awt.Color(102, 102, 102));

        btnDTLuongVe.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnDTLuongVe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDTLuongVe.setText("Lượng vé đã bán");
        btnDTLuongVe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDTLuongVe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDTLuongVeMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnDTLuongVeMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlLVLayout = new javax.swing.GroupLayout(pnlLV);
        pnlLV.setLayout(pnlLVLayout);
        pnlLVLayout.setHorizontalGroup(
            pnlLVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 99, Short.MAX_VALUE)
            .addGroup(pnlLVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnDTLuongVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlLVLayout.setVerticalGroup(
            pnlLVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(pnlLVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnDTLuongVe, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
        );

        pnlControl.add(pnlLV);

        pnlKG.setBackground(new java.awt.Color(102, 102, 102));

        btnKhungGio.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnKhungGio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnKhungGio.setText("Khung giờ");
        btnKhungGio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKhungGio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKhungGioMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnKhungGioMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlKGLayout = new javax.swing.GroupLayout(pnlKG);
        pnlKG.setLayout(pnlKGLayout);
        pnlKGLayout.setHorizontalGroup(
            pnlKGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
            .addGroup(pnlKGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnKhungGio, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
        );
        pnlKGLayout.setVerticalGroup(
            pnlKGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(pnlKGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnKhungGio, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
        );

        pnlControl.add(pnlKG);

        pnlMenu.add(pnlControl);
        pnlControl.setBounds(0, 70, 700, 40);

        tabsTonghopthongke.setMaximumSize(new java.awt.Dimension(690, 590));
        tabsTonghopthongke.setPreferredSize(new java.awt.Dimension(690, 590));

        pnlBD_DTTong.setOpaque(false);
        pnlBD_DTTong.setLayout(new java.awt.BorderLayout());

        pnlBD_DTLuongVe.setLayout(new java.awt.BorderLayout());

        pnlBD_DTPhim.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout pnlBieuDoLayout = new javax.swing.GroupLayout(pnlBieuDo);
        pnlBieuDo.setLayout(pnlBieuDoLayout);
        pnlBieuDoLayout.setHorizontalGroup(
            pnlBieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBieuDoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBD_DTPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlBieuDoLayout.createSequentialGroup()
                        .addComponent(pnlBD_DTTong, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlBD_DTLuongVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlBieuDoLayout.setVerticalGroup(
            pnlBieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBieuDoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlBieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlBD_DTTong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlBD_DTLuongVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBD_DTPhim, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlBieuDoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {pnlBD_DTLuongVe, pnlBD_DTTong});

        tabsTonghopthongke.addTab("BieuDo", pnlBieuDo);

        pnlDTTong.setPreferredSize(new java.awt.Dimension(683, 545));

        tblDoanhThuTong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Doanh thu phòng vé", "Doanh thu đồ ăn", "Tổng doanh thu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDoanhThuTong.setPreferredSize(new java.awt.Dimension(300, 320));
        jScrollPane5.setViewportView(tblDoanhThuTong);

        btnXuatFileExcel_DTTong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/xuatfileexel.jpg"))); // NOI18N
        btnXuatFileExcel_DTTong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileExcel_DTTongActionPerformed(evt);
            }
        });

        lblXuatFileExcel4.setText("Xuất file excel");

        pnl1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bộ lọc", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(153, 153, 255))); // NOI18N
        pnl1.setForeground(new java.awt.Color(204, 204, 204));

        btgDoanhThuTong.add(rdoTheoThang_DTTong);
        rdoTheoThang_DTTong.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        rdoTheoThang_DTTong.setText("Theo tháng");
        rdoTheoThang_DTTong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoThang_DTTongActionPerformed(evt);
            }
        });

        cboThangTheoThang_DTTong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cboThangTheoThang_DTTong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThangTheoThang_DTTongActionPerformed(evt);
            }
        });

        cboNamTheoThang_DTTong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2021", "2022" }));
        cboNamTheoThang_DTTong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamTheoThang_DTTongActionPerformed(evt);
            }
        });

        btgDoanhThuTong.add(rdoTheoNam_DTTong);
        rdoTheoNam_DTTong.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        rdoTheoNam_DTTong.setText("Theo năm");
        rdoTheoNam_DTTong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoNam_DTTongActionPerformed(evt);
            }
        });

        cboTheoNam_DTTong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2021", "2022" }));
        cboTheoNam_DTTong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTheoNam_DTTongActionPerformed(evt);
            }
        });

        btgDoanhThuTong.add(rdoTheoNgay_DTTong);
        rdoTheoNgay_DTTong.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        rdoTheoNgay_DTTong.setText("Theo ngày");
        rdoTheoNgay_DTTong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoNgay_DTTongActionPerformed(evt);
            }
        });

        dcsTheoNgay_DTTong.setDateFormatString("dd/MM/yyyy");
        dcsTheoNgay_DTTong.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcsTheoNgay_DTTongPropertyChange(evt);
            }
        });

        btnHienThiTatCaDanhMuc_DTTong.setText("Hiển thị theo bộ lọc");
        btnHienThiTatCaDanhMuc_DTTong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHienThiTatCaDanhMuc_DTTongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl1Layout = new javax.swing.GroupLayout(pnl1);
        pnl1.setLayout(pnl1Layout);
        pnl1Layout.setHorizontalGroup(
            pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cboTheoNam_DTTong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnl1Layout.createSequentialGroup()
                        .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoTheoNam_DTTong)
                            .addComponent(rdoTheoThang_DTTong)
                            .addComponent(rdoTheoNgay_DTTong))
                        .addGap(66, 66, 66))
                    .addComponent(btnHienThiTatCaDanhMuc_DTTong, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnl1Layout.createSequentialGroup()
                        .addComponent(cboThangTheoThang_DTTong, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboNamTheoThang_DTTong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(dcsTheoNgay_DTTong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 6, Short.MAX_VALUE))
        );
        pnl1Layout.setVerticalGroup(
            pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHienThiTatCaDanhMuc_DTTong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rdoTheoNgay_DTTong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dcsTheoNgay_DTTong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rdoTheoThang_DTTong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboThangTheoThang_DTTong)
                    .addComponent(cboNamTheoThang_DTTong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(rdoTheoNam_DTTong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboTheoNam_DTTong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnBieuDoNgay_DTTong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bieudo.jpg"))); // NOI18N
        btnBieuDoNgay_DTTong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBieuDoNgay_DTTongActionPerformed(evt);
            }
        });

        lblBieuDoNgay3.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        lblBieuDoNgay3.setText("Biểu đồ doanh thu tổng");

        javax.swing.GroupLayout pnlDTTongLayout = new javax.swing.GroupLayout(pnlDTTong);
        pnlDTTong.setLayout(pnlDTTongLayout);
        pnlDTTongLayout.setHorizontalGroup(
            pnlDTTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDTTongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlDTTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDTTongLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(9, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDTTongLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlDTTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDTTongLayout.createSequentialGroup()
                                .addComponent(btnBieuDoNgay_DTTong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56))
                            .addComponent(lblBieuDoNgay3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlDTTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblXuatFileExcel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDTTongLayout.createSequentialGroup()
                                .addComponent(btnXuatFileExcel_DTTong, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)))
                        .addContainerGap())))
        );
        pnlDTTongLayout.setVerticalGroup(
            pnlDTTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDTTongLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlDTTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlDTTongLayout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlDTTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDTTongLayout.createSequentialGroup()
                                .addComponent(btnXuatFileExcel_DTTong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblXuatFileExcel4))
                            .addGroup(pnlDTTongLayout.createSequentialGroup()
                                .addComponent(btnBieuDoNgay_DTTong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblBieuDoNgay3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 98, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tabsTonghopthongke.addTab("Doanh thu tổng", pnlDTTong);

        pnlDTPhim.setMaximumSize(new java.awt.Dimension(683, 570));
        pnlDTPhim.setPreferredSize(new java.awt.Dimension(683, 570));

        pnl3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bộ lọc", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(153, 153, 255))); // NOI18N
        pnl3.setForeground(new java.awt.Color(204, 204, 204));

        btgPhim.add(rdoTheoThang_DTPhim);
        rdoTheoThang_DTPhim.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        rdoTheoThang_DTPhim.setText("Theo tháng");
        rdoTheoThang_DTPhim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoThang_DTPhimActionPerformed(evt);
            }
        });

        cboThangTheoThang_DTPhim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cboThangTheoThang_DTPhim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThangTheoThang_DTPhimActionPerformed(evt);
            }
        });

        cboNamTheoThang_DTPhim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2021", "2022" }));
        cboNamTheoThang_DTPhim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamTheoThang_DTPhimActionPerformed(evt);
            }
        });

        btgPhim.add(rdoTheoNam_DTPhim);
        rdoTheoNam_DTPhim.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        rdoTheoNam_DTPhim.setText("Theo năm");
        rdoTheoNam_DTPhim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoNam_DTPhimActionPerformed(evt);
            }
        });

        cboTheoNam_DTPhim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2021", "2022" }));
        cboTheoNam_DTPhim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTheoNam_DTPhimActionPerformed(evt);
            }
        });

        btgPhim.add(rdoTheoNgay_DTPhim);
        rdoTheoNgay_DTPhim.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        rdoTheoNgay_DTPhim.setText("Theo ngày");
        rdoTheoNgay_DTPhim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoNgay_DTPhimActionPerformed(evt);
            }
        });

        dcsTheoNgay_DTPhim.setDateFormatString("dd/MM/yyyy");
        dcsTheoNgay_DTPhim.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcsTheoNgay_DTPhimPropertyChange(evt);
            }
        });

        btnHienThiTatCaDanhMuc_DTPhim.setText("Hiển thị theo bộ lọc");
        btnHienThiTatCaDanhMuc_DTPhim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHienThiTatCaDanhMuc_DTPhimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl3Layout = new javax.swing.GroupLayout(pnl3);
        pnl3.setLayout(pnl3Layout);
        pnl3Layout.setHorizontalGroup(
            pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cboTheoNam_DTPhim, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnl3Layout.createSequentialGroup()
                        .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoTheoNam_DTPhim)
                            .addComponent(rdoTheoThang_DTPhim)
                            .addComponent(rdoTheoNgay_DTPhim))
                        .addGap(66, 66, 66))
                    .addComponent(btnHienThiTatCaDanhMuc_DTPhim, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnl3Layout.createSequentialGroup()
                        .addComponent(cboThangTheoThang_DTPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboNamTheoThang_DTPhim, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(dcsTheoNgay_DTPhim, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 6, Short.MAX_VALUE))
        );
        pnl3Layout.setVerticalGroup(
            pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHienThiTatCaDanhMuc_DTPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rdoTheoNgay_DTPhim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dcsTheoNgay_DTPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rdoTheoThang_DTPhim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboThangTheoThang_DTPhim)
                    .addComponent(cboNamTheoThang_DTPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(rdoTheoNam_DTPhim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboTheoNam_DTPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblDTPhim.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên phim", "Số suất chiếu", "Số lượng vé", "Doanh thu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDTPhim.setPreferredSize(new java.awt.Dimension(300, 320));
        jScrollPane4.setViewportView(tblDTPhim);

        btnXuatFileExcel_DTPhim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/xuatfileexel.jpg"))); // NOI18N
        btnXuatFileExcel_DTPhim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileExcel_DTPhimActionPerformed(evt);
            }
        });

        lblXuatFileExcel3.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        lblXuatFileExcel3.setText("Xuất file Excel");

        btnSLVeGiam.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        btnSLVeGiam.setText("Sắp xếp lượng vé giảm dần");
        btnSLVeGiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSLVeGiamActionPerformed(evt);
            }
        });

        btnTop5DoanhThu.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        btnTop5DoanhThu.setText("Top 5 phim có doanh thu cao nhất");
        btnTop5DoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTop5DoanhThuActionPerformed(evt);
            }
        });

        btnBieuDoNgay_DTPhim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bieudo.jpg"))); // NOI18N
        btnBieuDoNgay_DTPhim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBieuDoNgay_DTPhimActionPerformed(evt);
            }
        });

        lblBieuDoNgay2.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        lblBieuDoNgay2.setText("Biểu đồ doanh thu phim");

        javax.swing.GroupLayout pnlDTPhimLayout = new javax.swing.GroupLayout(pnlDTPhim);
        pnlDTPhim.setLayout(pnlDTPhimLayout);
        pnlDTPhimLayout.setHorizontalGroup(
            pnlDTPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDTPhimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlDTPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDTPhimLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlDTPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBieuDoNgay2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDTPhimLayout.createSequentialGroup()
                                .addComponent(btnBieuDoNgay_DTPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)))
                        .addGroup(pnlDTPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDTPhimLayout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(btnXuatFileExcel_DTPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlDTPhimLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblXuatFileExcel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12))
                    .addGroup(pnlDTPhimLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(pnlDTPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDTPhimLayout.createSequentialGroup()
                                .addComponent(btnSLVeGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTop5DoanhThu)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlDTPhimLayout.setVerticalGroup(
            pnlDTPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDTPhimLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlDTPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDTPhimLayout.createSequentialGroup()
                        .addGroup(pnlDTPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSLVeGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTop5DoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlDTPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDTPhimLayout.createSequentialGroup()
                                .addComponent(btnXuatFileExcel_DTPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblXuatFileExcel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDTPhimLayout.createSequentialGroup()
                                .addComponent(btnBieuDoNgay_DTPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblBieuDoNgay2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 107, Short.MAX_VALUE))
                    .addComponent(pnl3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabsTonghopthongke.addTab("Doanh thu phim", pnlDTPhim);

        pnl6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bộ lọc", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(153, 153, 255))); // NOI18N
        pnl6.setForeground(new java.awt.Color(204, 204, 204));

        dscTheoNgay_DTDoAn.setDateFormatString("dd/MM/yyyy");
        dscTheoNgay_DTDoAn.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dscTheoNgay_DTDoAnPropertyChange(evt);
            }
        });

        btgDoAn_TG.add(rdoTheoNgay_DTDoAn);
        rdoTheoNgay_DTDoAn.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        rdoTheoNgay_DTDoAn.setText("Theo ngày");
        rdoTheoNgay_DTDoAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoNgay_DTDoAnActionPerformed(evt);
            }
        });

        btgDoAn_TG.add(rdoTheoThang_DTDoAn);
        rdoTheoThang_DTDoAn.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        rdoTheoThang_DTDoAn.setText("Theo tháng");
        rdoTheoThang_DTDoAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoThang_DTDoAnActionPerformed(evt);
            }
        });

        btgDoAn_TG.add(rdoTheoNam_DTDoAn);
        rdoTheoNam_DTDoAn.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        rdoTheoNam_DTDoAn.setText("Theo năm");
        rdoTheoNam_DTDoAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoNam_DTDoAnActionPerformed(evt);
            }
        });

        cboThangTheoThang_DTDoAn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cboThangTheoThang_DTDoAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThangTheoThang_DTDoAnActionPerformed(evt);
            }
        });

        cboNamTheoThang_DTDoAn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2021", "2022" }));
        cboNamTheoThang_DTDoAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamTheoThang_DTDoAnActionPerformed(evt);
            }
        });

        cboTheoNam_DTDoAN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2021", "2022" }));
        cboTheoNam_DTDoAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTheoNam_DTDoANActionPerformed(evt);
            }
        });

        btgDoAn_TG.add(rdoTheoTenDoAn);
        rdoTheoTenDoAn.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        rdoTheoTenDoAn.setText("Theo tên đồ ăn");
        rdoTheoTenDoAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoTenDoAnActionPerformed(evt);
            }
        });

        btgDoAn_TG.add(rdoTheoSize);
        rdoTheoSize.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        rdoTheoSize.setText("Theo size");
        rdoTheoSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoSizeActionPerformed(evt);
            }
        });

        cboTheoTenDoAn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nước lọc", "Pepsi", "Cocacola", "Trà sữa", "Cà phê", "Pazza", "Bắp rang bơ", "Sushi", "Bánh tráng trộn", "Bánh ngọt" }));
        cboTheoTenDoAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTheoTenDoAnActionPerformed(evt);
            }
        });

        cboTheoSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S", "M", "L" }));
        cboTheoSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTheoSizeActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel1.setText("Theo thời gian");

        jLabel2.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel2.setText("Theo sản phẩm");

        btnHienThiTatCaDanhMuc_DTDoAN.setText("Hiển thị tất cả danh mục");
        btnHienThiTatCaDanhMuc_DTDoAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHienThiTatCaDanhMuc_DTDoANActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl6Layout = new javax.swing.GroupLayout(pnl6);
        pnl6.setLayout(pnl6Layout);
        pnl6Layout.setHorizontalGroup(
            pnl6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cboNamTheoThang_DTDoAn, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnl6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnl6Layout.createSequentialGroup()
                        .addGroup(pnl6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboTheoSize, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboTheoTenDoAn, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboTheoNam_DTDoAN, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHienThiTatCaDanhMuc_DTDoAN, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(dscTheoNgay_DTDoAn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnl6Layout.createSequentialGroup()
                                .addGroup(pnl6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboThangTheoThang_DTDoAn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdoTheoSize)
                                    .addComponent(rdoTheoThang_DTDoAn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdoTheoNam_DTDoAn)
                                    .addComponent(rdoTheoTenDoAn)
                                    .addComponent(rdoTheoNgay_DTDoAn))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        pnl6Layout.setVerticalGroup(
            pnl6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHienThiTatCaDanhMuc_DTDoAN, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoTheoNgay_DTDoAn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dscTheoNgay_DTDoAn, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdoTheoThang_DTDoAn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboThangTheoThang_DTDoAn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNamTheoThang_DTDoAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(rdoTheoNam_DTDoAn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTheoNam_DTDoAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoTheoTenDoAn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTheoTenDoAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdoTheoSize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTheoSize, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pnl6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboNamTheoThang_DTDoAn, cboThangTheoThang_DTDoAn, cboTheoNam_DTDoAN, cboTheoTenDoAn});

        tblDoanhThuDoAn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên đồ ăn", "Số lượng", "Size", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tblDoanhThuDoAn);

        btnXuatFileExcel_DoAnDaBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/xuatfileexel.jpg"))); // NOI18N
        btnXuatFileExcel_DoAnDaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileExcel_DoAnDaBanActionPerformed(evt);
            }
        });

        lblXuatFileExcel5.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        lblXuatFileExcel5.setText("Xuất file excel");

        javax.swing.GroupLayout pnlDTDoAnLayout = new javax.swing.GroupLayout(pnlDTDoAn);
        pnlDTDoAn.setLayout(pnlDTDoAnLayout);
        pnlDTDoAnLayout.setHorizontalGroup(
            pnlDTDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDTDoAnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlDTDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDTDoAnLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 8, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDTDoAnLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlDTDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDTDoAnLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(btnXuatFileExcel_DoAnDaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblXuatFileExcel5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16))))
        );
        pnlDTDoAnLayout.setVerticalGroup(
            pnlDTDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDTDoAnLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlDTDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnl6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlDTDoAnLayout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlDTDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDTDoAnLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(lblXuatFileExcel5))
                            .addComponent(btnXuatFileExcel_DoAnDaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tabsTonghopthongke.addTab("DTDA", pnlDTDoAn);

        pnlPhongVe.setMaximumSize(new java.awt.Dimension(695, 570));
        pnlPhongVe.setPreferredSize(new java.awt.Dimension(695, 570));

        pnl2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bộ lọc", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(153, 153, 255))); // NOI18N
        pnl2.setForeground(new java.awt.Color(204, 204, 204));
        pnl2.setMaximumSize(new java.awt.Dimension(218, 483));
        pnl2.setPreferredSize(new java.awt.Dimension(218, 483));

        btgPhongVe.add(rdoTheoThang_DTPhongVe);
        rdoTheoThang_DTPhongVe.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        rdoTheoThang_DTPhongVe.setText("Theo tháng");
        rdoTheoThang_DTPhongVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoThang_DTPhongVeActionPerformed(evt);
            }
        });

        btgPhongVe.add(rdoTheoNam_DTPhongVe);
        rdoTheoNam_DTPhongVe.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        rdoTheoNam_DTPhongVe.setText("Theo năm");
        rdoTheoNam_DTPhongVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoNam_DTPhongVeActionPerformed(evt);
            }
        });

        cboThangTheoThang_DTPhongVe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboThangTheoThang_DTPhongVe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cboThangTheoThang_DTPhongVe.setMaximumSize(new java.awt.Dimension(56, 26));
        cboThangTheoThang_DTPhongVe.setPreferredSize(new java.awt.Dimension(56, 26));
        cboThangTheoThang_DTPhongVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThangTheoThang_DTPhongVeActionPerformed(evt);
            }
        });

        cboNamTheoThang_DTPhongVe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboNamTheoThang_DTPhongVe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2021", "2022" }));
        cboNamTheoThang_DTPhongVe.setMaximumSize(new java.awt.Dimension(56, 26));
        cboNamTheoThang_DTPhongVe.setPreferredSize(new java.awt.Dimension(56, 26));
        cboNamTheoThang_DTPhongVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamTheoThang_DTPhongVeActionPerformed(evt);
            }
        });

        cboTheoNam_DTPhongVe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboTheoNam_DTPhongVe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2021", "2022" }));
        cboTheoNam_DTPhongVe.setMinimumSize(new java.awt.Dimension(56, 26));
        cboTheoNam_DTPhongVe.setPreferredSize(new java.awt.Dimension(56, 26));
        cboTheoNam_DTPhongVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTheoNam_DTPhongVeActionPerformed(evt);
            }
        });

        btgPhongVe.add(rdoTheoNgay_DTPhongVe);
        rdoTheoNgay_DTPhongVe.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        rdoTheoNgay_DTPhongVe.setText("Theo ngày");
        rdoTheoNgay_DTPhongVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoNgay_DTPhongVeActionPerformed(evt);
            }
        });

        dcsNgay_DTPhongVe.setDateFormatString("dd/MM/yyyy");
        dcsNgay_DTPhongVe.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcsNgay_DTPhongVePropertyChange(evt);
            }
        });

        btnHienThiTatCaDanhMuc_DTPhongVe.setText("Hiển thị tất cả danh mục");
        btnHienThiTatCaDanhMuc_DTPhongVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHienThiTatCaDanhMuc_DTPhongVeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl2Layout = new javax.swing.GroupLayout(pnl2);
        pnl2.setLayout(pnl2Layout);
        pnl2Layout.setHorizontalGroup(
            pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcsNgay_DTPhongVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboTheoNam_DTPhongVe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnl2Layout.createSequentialGroup()
                        .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoTheoNam_DTPhongVe)
                            .addComponent(rdoTheoThang_DTPhongVe)
                            .addComponent(rdoTheoNgay_DTPhongVe))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnl2Layout.createSequentialGroup()
                        .addComponent(cboThangTheoThang_DTPhongVe, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(cboNamTheoThang_DTPhongVe, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnHienThiTatCaDanhMuc_DTPhongVe, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnl2Layout.setVerticalGroup(
            pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(btnHienThiTatCaDanhMuc_DTPhongVe, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdoTheoNgay_DTPhongVe)
                .addGap(8, 8, 8)
                .addComponent(dcsNgay_DTPhongVe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdoTheoThang_DTPhongVe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboThangTheoThang_DTPhongVe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNamTheoThang_DTPhongVe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdoTheoNam_DTPhongVe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTheoNam_DTPhongVe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(229, Short.MAX_VALUE))
        );

        tblDTPhongVe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tháng", "Tổng số suất chiếu", "Số vé đã bán", "Doanh thu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblDTPhongVe);

        btnXuatFileExcel_DTPhongVe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/xuatfileexel.jpg"))); // NOI18N
        btnXuatFileExcel_DTPhongVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileExcel_DTPhongVeActionPerformed(evt);
            }
        });

        lblXuatFileExcel2.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        lblXuatFileExcel2.setText("Xuất file excel");

        javax.swing.GroupLayout pnlPhongVeLayout = new javax.swing.GroupLayout(pnlPhongVe);
        pnlPhongVe.setLayout(pnlPhongVeLayout);
        pnlPhongVeLayout.setHorizontalGroup(
            pnlPhongVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPhongVeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPhongVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPhongVeLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlPhongVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPhongVeLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(btnXuatFileExcel_DTPhongVe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblXuatFileExcel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pnlPhongVeLayout.setVerticalGroup(
            pnlPhongVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPhongVeLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlPhongVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnl2, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                    .addGroup(pnlPhongVeLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlPhongVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPhongVeLayout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(lblXuatFileExcel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnXuatFileExcel_DTPhongVe, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tabsTonghopthongke.addTab("DTPV", pnlPhongVe);

        pnlVeTheoNgay.setMaximumSize(new java.awt.Dimension(697, 570));
        pnlVeTheoNgay.setPreferredSize(new java.awt.Dimension(697, 570));

        tblLuongVeDaBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Ngày bán", "Lượng vé đã bán", "Suất chiếu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLuongVeDaBan.setPreferredSize(new java.awt.Dimension(300, 320));
        jScrollPane1.setViewportView(tblLuongVeDaBan);

        pnl4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bộ lọc", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(153, 153, 255))); // NOI18N
        pnl4.setForeground(new java.awt.Color(204, 204, 204));

        btgLuongVe.add(rdoTheoThang_VeDaBan);
        rdoTheoThang_VeDaBan.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        rdoTheoThang_VeDaBan.setText("Theo tháng");
        rdoTheoThang_VeDaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoThang_VeDaBanActionPerformed(evt);
            }
        });

        btgLuongVe.add(rdoTheoNam_VeDaBan);
        rdoTheoNam_VeDaBan.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        rdoTheoNam_VeDaBan.setText("Theo năm");
        rdoTheoNam_VeDaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoNam_VeDaBanActionPerformed(evt);
            }
        });

        cboThangTheoThang_Vedaban.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboThangTheoThang_Vedaban.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cboThangTheoThang_Vedaban.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThangTheoThang_VedabanActionPerformed(evt);
            }
        });

        cboNamTheoThang_Vedaban.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboNamTheoThang_Vedaban.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2021", "2022" }));
        cboNamTheoThang_Vedaban.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamTheoThang_VedabanActionPerformed(evt);
            }
        });

        cboTheoNam_VeDaBan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboTheoNam_VeDaBan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2021", "2022" }));
        cboTheoNam_VeDaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTheoNam_VeDaBanActionPerformed(evt);
            }
        });

        btnHienThiTatCaDanhMuc_DTLuongVe.setText("Hiển thị theo bộ lọc");
        btnHienThiTatCaDanhMuc_DTLuongVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHienThiTatCaDanhMuc_DTLuongVeActionPerformed(evt);
            }
        });

        btgLuongVe.add(rdoTheoNgay_LuongVeDB);
        rdoTheoNgay_LuongVeDB.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        rdoTheoNgay_LuongVeDB.setText("Theo ngày");
        rdoTheoNgay_LuongVeDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoNgay_LuongVeDBActionPerformed(evt);
            }
        });

        dcsTheoNgay_LuongVeDB.setDateFormatString("dd/MM/yyyy");
        dcsTheoNgay_LuongVeDB.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcsTheoNgay_LuongVeDBPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout pnl4Layout = new javax.swing.GroupLayout(pnl4);
        pnl4.setLayout(pnl4Layout);
        pnl4Layout.setHorizontalGroup(
            pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcsTheoNgay_LuongVeDB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboTheoNam_VeDaBan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHienThiTatCaDanhMuc_DTLuongVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnl4Layout.createSequentialGroup()
                        .addComponent(cboThangTheoThang_Vedaban, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(cboNamTheoThang_Vedaban, 0, 80, Short.MAX_VALUE))
                    .addGroup(pnl4Layout.createSequentialGroup()
                        .addGroup(pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoTheoThang_VeDaBan)
                            .addComponent(rdoTheoNam_VeDaBan)
                            .addComponent(rdoTheoNgay_LuongVeDB))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnl4Layout.setVerticalGroup(
            pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHienThiTatCaDanhMuc_DTLuongVe, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdoTheoNgay_LuongVeDB)
                .addGap(7, 7, 7)
                .addComponent(dcsTheoNgay_LuongVeDB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoTheoThang_VeDaBan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboThangTheoThang_Vedaban, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNamTheoThang_Vedaban, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdoTheoNam_VeDaBan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTheoNam_VeDaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnXuatFileExcel_VeDaBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/xuatfileexel.jpg"))); // NOI18N
        btnXuatFileExcel_VeDaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileExcel_VeDaBanActionPerformed(evt);
            }
        });

        lblXuatFileExcel.setText("Xuất file excel");

        btnBieuDoNgay_LuongVeDB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bieudo.jpg"))); // NOI18N
        btnBieuDoNgay_LuongVeDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBieuDoNgay_LuongVeDBActionPerformed(evt);
            }
        });

        lblBieuDoNgay4.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        lblBieuDoNgay4.setText("Biểu đồ số lượng vé bán");

        javax.swing.GroupLayout pnlVeTheoNgayLayout = new javax.swing.GroupLayout(pnlVeTheoNgay);
        pnlVeTheoNgay.setLayout(pnlVeTheoNgayLayout);
        pnlVeTheoNgayLayout.setHorizontalGroup(
            pnlVeTheoNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVeTheoNgayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlVeTheoNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVeTheoNgayLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE))
                    .addGroup(pnlVeTheoNgayLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlVeTheoNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBieuDoNgay4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVeTheoNgayLayout.createSequentialGroup()
                                .addComponent(btnBieuDoNgay_LuongVeDB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlVeTheoNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVeTheoNgayLayout.createSequentialGroup()
                                .addComponent(btnXuatFileExcel_VeDaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26))
                            .addComponent(lblXuatFileExcel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pnlVeTheoNgayLayout.setVerticalGroup(
            pnlVeTheoNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVeTheoNgayLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlVeTheoNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnl4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlVeTheoNgayLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlVeTheoNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlVeTheoNgayLayout.createSequentialGroup()
                                .addComponent(btnXuatFileExcel_VeDaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblXuatFileExcel))
                            .addGroup(pnlVeTheoNgayLayout.createSequentialGroup()
                                .addComponent(btnBieuDoNgay_LuongVeDB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblBieuDoNgay4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 138, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tabsTonghopthongke.addTab("LVDB", pnlVeTheoNgay);

        pnlKhungGio.setMaximumSize(new java.awt.Dimension(695, 570));
        pnlKhungGio.setPreferredSize(new java.awt.Dimension(695, 570));

        pnl5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bộ lọc", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(153, 153, 255))); // NOI18N
        pnl5.setForeground(new java.awt.Color(204, 204, 204));

        dcsTheoNgay_KhungGio.setDateFormatString("dd/MM/yyyy");
        dcsTheoNgay_KhungGio.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcsTheoNgay_KhungGioPropertyChange(evt);
            }
        });

        btnHienThiTatCaDanhMuc_KhungGio.setText("Hiển thị tất cả danh mục");
        btnHienThiTatCaDanhMuc_KhungGio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHienThiTatCaDanhMuc_KhungGio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHienThiTatCaDanhMuc_KhungGioActionPerformed(evt);
            }
        });

        cboChonKhungGio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "4" }));
        cboChonKhungGio.setSelectedIndex(2);
        cboChonKhungGio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cboChonKhungGio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboChonKhungGioItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel3.setText("Theo ngày");

        jLabel4.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel4.setText("Theo khoảng khung giờ");

        javax.swing.GroupLayout pnl5Layout = new javax.swing.GroupLayout(pnl5);
        pnl5.setLayout(pnl5Layout);
        pnl5Layout.setHorizontalGroup(
            pnl5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl5Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl5Layout.createSequentialGroup()
                        .addGroup(pnl5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnHienThiTatCaDanhMuc_KhungGio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboChonKhungGio, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dcsTheoNgay_KhungGio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnl5Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 19, Short.MAX_VALUE)))
                        .addGap(4, 4, 4))))
        );
        pnl5Layout.setVerticalGroup(
            pnl5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHienThiTatCaDanhMuc_KhungGio, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dcsTheoNgay_KhungGio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboChonKhungGio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblXuatFileExcel1.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        lblXuatFileExcel1.setText("Xuất file Excel");

        btnXuatFileExcel_TKKhungGio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/xuatfileexel.jpg"))); // NOI18N
        btnXuatFileExcel_TKKhungGio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileExcel_TKKhungGioActionPerformed(evt);
            }
        });

        tblThongKeKhungGio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Khung giờ", "Số suất chiếu", "Số vé đã bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tblThongKeKhungGio);

        javax.swing.GroupLayout pnlKhungGioLayout = new javax.swing.GroupLayout(pnlKhungGio);
        pnlKhungGio.setLayout(pnlKhungGioLayout);
        pnlKhungGioLayout.setHorizontalGroup(
            pnlKhungGioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhungGioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlKhungGioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKhungGioLayout.createSequentialGroup()
                        .addGap(387, 387, 387)
                        .addGroup(pnlKhungGioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblXuatFileExcel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKhungGioLayout.createSequentialGroup()
                                .addComponent(btnXuatFileExcel_TKKhungGio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKhungGioLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlKhungGioLayout.setVerticalGroup(
            pnlKhungGioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhungGioLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlKhungGioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnl5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlKhungGioLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXuatFileExcel_TKKhungGio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblXuatFileExcel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 122, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tabsTonghopthongke.addTab("TKKG", pnlKhungGio);

        pnlMenu.add(tabsTonghopthongke);
        tabsTonghopthongke.setBounds(0, 70, 700, 580);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXemBieuDoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXemBieuDoMouseClicked
        // TODO add your handling code here:
        tabsTonghopthongke.setSelectedIndex(0);
    }//GEN-LAST:event_btnXemBieuDoMouseClicked

    private void btnKhungGioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhungGioMouseClicked
        // TODO add your handling code here:
        tabsTonghopthongke.setSelectedIndex(6);
    }//GEN-LAST:event_btnKhungGioMouseClicked

    private void btnDTLuongVeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDTLuongVeMouseClicked
        // TODO add your handling code here:
        tabsTonghopthongke.setSelectedIndex(5);
    }//GEN-LAST:event_btnDTLuongVeMouseClicked

    private void btnDTDoAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDTDoAnMouseClicked
        // TODO add your handling code here:
        tabsTonghopthongke.setSelectedIndex(3);
    }//GEN-LAST:event_btnDTDoAnMouseClicked

    private void btnDTPhimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDTPhimMouseClicked
        // TODO add your handling code here:
        tabsTonghopthongke.setSelectedIndex(2);
    }//GEN-LAST:event_btnDTPhimMouseClicked

    private void btnDTPhongVeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDTPhongVeMouseClicked
        // TODO add your handling code here:
        tabsTonghopthongke.setSelectedIndex(4);
    }//GEN-LAST:event_btnDTPhongVeMouseClicked

    private void btnDTTongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDTTongMouseClicked
        // TODO add your handling code here:
        tabsTonghopthongke.setSelectedIndex(1);
    }//GEN-LAST:event_btnDTTongMouseClicked

    private void btnXuatFileExcel_DTPhongVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileExcel_DTPhongVeActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser fileChooser = new JFileChooser();
            int retval = fileChooser.showSaveDialog(btnXuatFileExcel_DTPhongVe);

            if (retval == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (file != null) {
                    if (!file.getName().toLowerCase().endsWith(".xls")) {
                        file = new File(file.getParentFile(), file.getName() + ".xls");
                    }
                    try {
                        ExcelExporter exp = new ExcelExporter();
                        exp.exportTable(tblDTPhongVe, file, "Doanh thu phòng vé", 4);
                        Desktop.getDesktop().open(file);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("not found");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnXuatFileExcel_DTPhongVeActionPerformed

    private void btnXuatFileExcel_VeDaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileExcel_VeDaBanActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser fileChooser = new JFileChooser();
            int retval = fileChooser.showSaveDialog(btnXuatFileExcel_VeDaBan);

            if (retval == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (file != null) {
                    if (!file.getName().toLowerCase().endsWith(".xls")) {
                        file = new File(file.getParentFile(), file.getName() + ".xls");
                    }
                    try {
                        ExcelExporter exp = new ExcelExporter();
                        exp.exportTable(tblLuongVeDaBan, file, "Thống kê lượng vé đã bán", 3);
                        Desktop.getDesktop().open(file);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("not found");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnXuatFileExcel_VeDaBanActionPerformed

    private void cboTheoNam_DTPhimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTheoNam_DTPhimActionPerformed
        // TODO add your handling code here:
        try {
            if (rdoTheoNam_DTPhim.isSelected()) {
            fillTablePhim_TheoNam();
            fillBieuDo_DTPhim();
        }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        
    }//GEN-LAST:event_cboTheoNam_DTPhimActionPerformed

    private void cboNamTheoThang_DTPhimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamTheoThang_DTPhimActionPerformed
        // TODO add your handling code here:
        if (rdoTheoThang_DTPhim.isSelected()) {
            try {
                fillTablePhim_TheoThang();
                fillBieuDo_DTPhim();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_cboNamTheoThang_DTPhimActionPerformed

    private void rdoTheoNgay_DTPhimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTheoNgay_DTPhimActionPerformed
        // TODO add your handling code here:
        dcsTheoNgay_DTPhim.setEnabled(true);
        cboThangTheoThang_DTPhim.setEnabled(false);
        cboNamTheoThang_DTPhim.setEnabled(false);
        cboTheoNam_DTPhim.setEnabled(false);

    }//GEN-LAST:event_rdoTheoNgay_DTPhimActionPerformed

    private void rdoTheoThang_DTPhimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTheoThang_DTPhimActionPerformed
        // TODO add your handling code here:
        dcsTheoNgay_DTPhim.setEnabled(false);
        cboThangTheoThang_DTPhim.setEnabled(true);
        cboNamTheoThang_DTPhim.setEnabled(true);
        cboTheoNam_DTPhim.setEnabled(false);

    }//GEN-LAST:event_rdoTheoThang_DTPhimActionPerformed

    private void rdoTheoNam_DTPhimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTheoNam_DTPhimActionPerformed
        // TODO add your handling code here:
        dcsTheoNgay_DTPhim.setEnabled(false);
        cboThangTheoThang_DTPhim.setEnabled(false);
        cboNamTheoThang_DTPhim.setEnabled(false);
        cboTheoNam_DTPhim.setEnabled(true);

    }//GEN-LAST:event_rdoTheoNam_DTPhimActionPerformed

    private void dcsTheoNgay_DTPhimPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcsTheoNgay_DTPhimPropertyChange
        // TODO add your handling code here:
        if (rdoTheoNgay_DTPhim.isSelected()) {
            try {
                fillTablePhim_TheoNgay();
                fillBieuDo_DTPhim();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_dcsTheoNgay_DTPhimPropertyChange

    private void cboThangTheoThang_DTPhimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThangTheoThang_DTPhimActionPerformed
        // TODO add your handling code here:
        if (rdoTheoThang_DTPhim.isSelected()) {
            try {
                fillTablePhim_TheoThang();
                fillBieuDo_DTPhim();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }//GEN-LAST:event_cboThangTheoThang_DTPhimActionPerformed

    private void rdoTheoNgay_DTPhongVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTheoNgay_DTPhongVeActionPerformed
        // TODO add your handling code here:
        dcsNgay_DTPhongVe.setEnabled(true);
        cboThangTheoThang_DTPhongVe.setEnabled(false);
        cboNamTheoThang_DTPhongVe.setEnabled(false);
        cboTheoNam_DTPhongVe.setEnabled(false);

    }//GEN-LAST:event_rdoTheoNgay_DTPhongVeActionPerformed

    private void rdoTheoThang_DTPhongVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTheoThang_DTPhongVeActionPerformed
        // TODO add your handling code here:
        dcsNgay_DTPhongVe.setEnabled(false);
        cboThangTheoThang_DTPhongVe.setEnabled(true);
        cboNamTheoThang_DTPhongVe.setEnabled(true);
        cboTheoNam_DTPhongVe.setEnabled(false);

    }//GEN-LAST:event_rdoTheoThang_DTPhongVeActionPerformed

    private void rdoTheoNam_DTPhongVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTheoNam_DTPhongVeActionPerformed
        // TODO add your handling code here:
        dcsNgay_DTPhongVe.setEnabled(false);
        cboThangTheoThang_DTPhongVe.setEnabled(false);
        cboNamTheoThang_DTPhongVe.setEnabled(false);
        cboTheoNam_DTPhongVe.setEnabled(true);

    }//GEN-LAST:event_rdoTheoNam_DTPhongVeActionPerformed

    private void dcsNgay_DTPhongVePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcsNgay_DTPhongVePropertyChange
        // TODO add your handling code here:
        if (rdoTheoNgay_DTPhongVe.isSelected()) {
            try {
                fillTablePhongVe_TheoNgay();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_dcsNgay_DTPhongVePropertyChange

    private void cboThangTheoThang_DTPhongVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThangTheoThang_DTPhongVeActionPerformed
        // TODO add your handling code here:
        if (rdoTheoThang_DTPhongVe.isSelected()) {
            try {
                fillTablePhongVe_TheoThang();
            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_cboThangTheoThang_DTPhongVeActionPerformed

    private void cboNamTheoThang_DTPhongVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamTheoThang_DTPhongVeActionPerformed
        // TODO add your handling code here:
        if (rdoTheoThang_DTPhongVe.isSelected()) {
            try {
                fillTablePhongVe_TheoThang();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_cboNamTheoThang_DTPhongVeActionPerformed

    private void cboTheoNam_DTPhongVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTheoNam_DTPhongVeActionPerformed
        // TODO add your handling code here:
        if (rdoTheoNam_DTPhongVe.isSelected()) {
            try {
                fillTablePhongVe_TheoNam();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_cboTheoNam_DTPhongVeActionPerformed

    private void rdoTheoNgay_DTDoAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTheoNgay_DTDoAnActionPerformed
        // TODO add your handling code here:
        dscTheoNgay_DTDoAn.setEnabled(true);
        cboThangTheoThang_DTDoAn.setEnabled(false);
        cboNamTheoThang_DTDoAn.setEnabled(false);
        cboTheoNam_DTDoAN.setEnabled(false);
        cboTheoTenDoAn.setEnabled(false);
        cboTheoSize.setEnabled(false);

    }//GEN-LAST:event_rdoTheoNgay_DTDoAnActionPerformed

    private void rdoTheoThang_DTDoAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTheoThang_DTDoAnActionPerformed
        dscTheoNgay_DTDoAn.setEnabled(false);
        cboThangTheoThang_DTDoAn.setEnabled(true);
        cboNamTheoThang_DTDoAn.setEnabled(true);
        cboTheoNam_DTDoAN.setEnabled(false);
        cboTheoTenDoAn.setEnabled(false);
        cboTheoSize.setEnabled(false);

    }//GEN-LAST:event_rdoTheoThang_DTDoAnActionPerformed

    private void rdoTheoNam_DTDoAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTheoNam_DTDoAnActionPerformed
        // TODO add your handling code here:
        dscTheoNgay_DTDoAn.setEnabled(false);
        cboThangTheoThang_DTDoAn.setEnabled(false);
        cboNamTheoThang_DTDoAn.setEnabled(false);
        cboTheoNam_DTDoAN.setEnabled(true);
        cboTheoTenDoAn.setEnabled(false);
        cboTheoSize.setEnabled(false);

    }//GEN-LAST:event_rdoTheoNam_DTDoAnActionPerformed

    private void rdoTheoTenDoAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTheoTenDoAnActionPerformed
        // TODO add your handling code here:
//        dscTheoNgay_DTDoAn.setEnabled(false);
//        cboThangTheoThang_DTDoAn.setEnabled(false);
//        cboNamTheoThang_DTDoAn.setEnabled(false);
//        cboTheoNam_DTDoAN.setEnabled(false);
        cboTheoTenDoAn.setEnabled(true);
        cboTheoSize.setEnabled(false);

    }//GEN-LAST:event_rdoTheoTenDoAnActionPerformed

    private void rdoTheoSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTheoSizeActionPerformed
        // TODO add your handling code here:
//        dscTheoNgay_DTDoAn.setEnabled(false);
//        cboThangTheoThang_DTDoAn.setEnabled(false);
//        cboNamTheoThang_DTDoAn.setEnabled(false);
//        cboTheoNam_DTDoAN.setEnabled(false);
        cboTheoTenDoAn.setEnabled(false);
        cboTheoSize.setEnabled(true);

    }//GEN-LAST:event_rdoTheoSizeActionPerformed

    private void dscTheoNgay_DTDoAnPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dscTheoNgay_DTDoAnPropertyChange
        // TODO add your handling code here:
        if (rdoTheoNgay_DTDoAn.isSelected()) {
            try {
                fillTableDoAn_TheoNgay();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_dscTheoNgay_DTDoAnPropertyChange

    private void cboThangTheoThang_DTDoAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThangTheoThang_DTDoAnActionPerformed
        // TODO add your handling code here:
        try {
            if (rdoTheoThang_DTDoAn.isSelected()) {
                fillTableDoAn_TheoThang();
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_cboThangTheoThang_DTDoAnActionPerformed

    private void cboNamTheoThang_DTDoAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamTheoThang_DTDoAnActionPerformed
        // TODO add your handling code here:
        try {
            if (rdoTheoThang_DTDoAn.isSelected()) {
                fillTableDoAn_TheoThang();
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_cboNamTheoThang_DTDoAnActionPerformed

    private void cboTheoNam_DTDoANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTheoNam_DTDoANActionPerformed
        // TODO add your handling code here:
        if (rdoTheoNam_DTDoAn.isSelected()) {
            try {
                fillTableDoAn_TheoNam();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_cboTheoNam_DTDoANActionPerformed

    private void cboTheoTenDoAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTheoTenDoAnActionPerformed
        // TODO add your handling code here:
        if (rdoTheoTenDoAn.isSelected()) {
            try {
                fillTableDoAn_TheoTen();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_cboTheoTenDoAnActionPerformed

    private void cboTheoSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTheoSizeActionPerformed
        // TODO add your handling code here:
        if (rdoTheoSize.isSelected()) {
            try {
                fillTableDoAn_TheoSize();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_cboTheoSizeActionPerformed

    private void btnXuatFileExcel_DTTongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileExcel_DTTongActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser fileChooser = new JFileChooser();
            int retval = fileChooser.showSaveDialog(btnXuatFileExcel_DTTong);

            if (retval == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (file != null) {
                    if (!file.getName().toLowerCase().endsWith(".xls")) {
                        file = new File(file.getParentFile(), file.getName() + ".xls");
                    }
                    try {
                        ExcelExporter exp = new ExcelExporter();
                        exp.exportTable(tblDoanhThuTong, file, "Doanh thu tổng", 4);
                        Desktop.getDesktop().open(file);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("not found");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXuatFileExcel_DTTongActionPerformed

    private void btnXuatFileExcel_DTPhimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileExcel_DTPhimActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser fileChooser = new JFileChooser();
            int retval = fileChooser.showSaveDialog(btnXuatFileExcel_DTPhim);

            if (retval == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (file != null) {
                    if (!file.getName().toLowerCase().endsWith(".xls")) {
                        file = new File(file.getParentFile(), file.getName() + ".xls");
                    }
                    try {
                        ExcelExporter exp = new ExcelExporter();
                        exp.exportTable(tblDTPhim, file, "Doanh thu phim", 4);
                        Desktop.getDesktop().open(file);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("not found");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXuatFileExcel_DTPhimActionPerformed

    private void btnXuatFileExcel_DoAnDaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileExcel_DoAnDaBanActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser fileChooser = new JFileChooser();
            int retval = fileChooser.showSaveDialog(btnXuatFileExcel_DoAnDaBan);

            if (retval == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (file != null) {
                    if (!file.getName().toLowerCase().endsWith(".xls")) {
                        file = new File(file.getParentFile(), file.getName() + ".xls");
                    }
                    try {
                        ExcelExporter exp = new ExcelExporter();
                        exp.exportTable(tblDoanhThuDoAn, file, "Doanh thu đồ ăn", 4);
                        Desktop.getDesktop().open(file);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("not found");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnXuatFileExcel_DoAnDaBanActionPerformed

    private void btnXuatFileExcel_TKKhungGioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileExcel_TKKhungGioActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser fileChooser = new JFileChooser();
            int retval = fileChooser.showSaveDialog(btnXuatFileExcel_TKKhungGio);

            if (retval == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (file != null) {
                    if (!file.getName().toLowerCase().endsWith(".xls")) {
                        file = new File(file.getParentFile(), file.getName() + ".xls");
                    }
                    try {
                        ExcelExporter exp = new ExcelExporter();
                        exp.exportTable(tblThongKeKhungGio, file, "Thống kê khung giờ", 3);
                        Desktop.getDesktop().open(file);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("not found");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnXuatFileExcel_TKKhungGioActionPerformed

    private void btnHienThiTatCaDanhMuc_DTLuongVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHienThiTatCaDanhMuc_DTLuongVeActionPerformed
        // TODO add your handling code here:
        if (btnHienThiTatCaDanhMuc_DTLuongVe.isSelected()) {
            btnHienThiTatCaDanhMuc_DTLuongVe.setText(HIEN_THEO_BO_LOC_TEXT);
            buttonStatusOpen_LuongVe();
        } else {
            try {
                btnHienThiTatCaDanhMuc_DTLuongVe.setText(HIEN_TAT_CA_TEXT);
                buttonStatusClose_LuongVe();
                fillTableLuongVe();
                fillBieuDo_LuongVeDaBan();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnHienThiTatCaDanhMuc_DTLuongVeActionPerformed

    private void btnHienThiTatCaDanhMuc_DTPhimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHienThiTatCaDanhMuc_DTPhimActionPerformed
        // TODO add your handling code here:
        if (btnHienThiTatCaDanhMuc_DTPhim.isSelected()) {
            btnHienThiTatCaDanhMuc_DTPhim.setText(HIEN_THEO_BO_LOC_TEXT);
            buttonStatusOpen_Phim();            
        } else {
            try {
                btnHienThiTatCaDanhMuc_DTPhim.setText(HIEN_TAT_CA_TEXT);
                buttonStatusClose_Phim();
                fillTablePhim();
                fillBieuDo_DTPhim();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnHienThiTatCaDanhMuc_DTPhimActionPerformed

    private void btnHienThiTatCaDanhMuc_DTDoANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHienThiTatCaDanhMuc_DTDoANActionPerformed
        // TODO add your handling code here:
        if (btnHienThiTatCaDanhMuc_DTDoAN.isSelected()) {
            btnHienThiTatCaDanhMuc_DTDoAN.setText(HIEN_THEO_BO_LOC_TEXT);
            buttonStatusOpen_DoAn();
        } else {
            try {
                btnHienThiTatCaDanhMuc_DTDoAN.setText(HIEN_TAT_CA_TEXT);
                buttonStatusClose_DoAn();
                fillTableDoAn();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnHienThiTatCaDanhMuc_DTDoANActionPerformed

    private void btnHienThiTatCaDanhMuc_DTPhongVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHienThiTatCaDanhMuc_DTPhongVeActionPerformed
        // TODO add your handling code here:
        if (btnHienThiTatCaDanhMuc_DTPhongVe.isSelected()) {
            btnHienThiTatCaDanhMuc_DTPhongVe.setText(HIEN_THEO_BO_LOC_TEXT);
            buttonStatusOpen_PhongVe();
        } else {
            try {
                btnHienThiTatCaDanhMuc_DTPhongVe.setText(HIEN_TAT_CA_TEXT);
                buttonStatusClose_PhongVe();
                fillTablePhongVe();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnHienThiTatCaDanhMuc_DTPhongVeActionPerformed

    private void btnBieuDoNgay_DTPhimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBieuDoNgay_DTPhimActionPerformed
        // TODO add your handling code here:
        tabsTonghopthongke.setSelectedIndex(0);
    }//GEN-LAST:event_btnBieuDoNgay_DTPhimActionPerformed

    private void btnSLVeGiamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSLVeGiamActionPerformed
        try {
            fillTablePhim_SapXep();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnSLVeGiamActionPerformed

    private void btnTop5DoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTop5DoanhThuActionPerformed
        try {
            // TODO add your handling code here:
            fillTablePhim_Top();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnTop5DoanhThuActionPerformed

    private void rdoTheoThang_DTTongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTheoThang_DTTongActionPerformed
        // TODO add your handling code here:
        dcsTheoNgay_DTTong.setEnabled(false);
        cboThangTheoThang_DTTong.setEnabled(true);
        cboNamTheoThang_DTTong.setEnabled(true);
        cboTheoNam_DTTong.setEnabled(false);
    }//GEN-LAST:event_rdoTheoThang_DTTongActionPerformed

    private void cboThangTheoThang_DTTongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThangTheoThang_DTTongActionPerformed
        // TODO add your handling code here:
        try {
            if (rdoTheoThang_DTTong.isSelected()) {
                fillTableDTTong_Thang();
                fillBieuDo_DTTong();
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_cboThangTheoThang_DTTongActionPerformed

    private void cboNamTheoThang_DTTongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamTheoThang_DTTongActionPerformed
        // TODO add your handling code here:
        if (rdoTheoThang_DTTong.isSelected()) {
            try {
                fillTableDTTong_Thang();
                fillBieuDo_DTTong();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_cboNamTheoThang_DTTongActionPerformed

    private void rdoTheoNam_DTTongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTheoNam_DTTongActionPerformed
        // TODO add your handling code here:
        dcsTheoNgay_DTTong.setEnabled(false);
        cboThangTheoThang_DTTong.setEnabled(false);
        cboNamTheoThang_DTTong.setEnabled(false);
        cboTheoNam_DTTong.setEnabled(true);
    }//GEN-LAST:event_rdoTheoNam_DTTongActionPerformed

    private void cboTheoNam_DTTongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTheoNam_DTTongActionPerformed
        // TODO add your handling code here:
        if (rdoTheoNam_DTTong.isSelected()) {
            try {
                fillTableDTTong_Nam();
                fillBieuDo_DTTong();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_cboTheoNam_DTTongActionPerformed

    private void rdoTheoNgay_DTTongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTheoNgay_DTTongActionPerformed
        // TODO add your handling code here:
        dcsTheoNgay_DTTong.setEnabled(true);
        cboThangTheoThang_DTTong.setEnabled(false);
        cboNamTheoThang_DTTong.setEnabled(false);
        cboTheoNam_DTTong.setEnabled(false);
    }//GEN-LAST:event_rdoTheoNgay_DTTongActionPerformed

    private void dcsTheoNgay_DTTongPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcsTheoNgay_DTTongPropertyChange
        // TODO add your handling code here:
        if (rdoTheoNgay_DTTong.isSelected()) {
            try {
                fillTableDTTong_Ngay();
                fillBieuDo_DTTong();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_dcsTheoNgay_DTTongPropertyChange

    private void btnHienThiTatCaDanhMuc_DTTongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHienThiTatCaDanhMuc_DTTongActionPerformed
        // TODO add your handling code here:
        if (btnHienThiTatCaDanhMuc_DTTong.isSelected()) {
            btnHienThiTatCaDanhMuc_DTTong.setText(HIEN_THEO_BO_LOC_TEXT);
            buttonStatusOpen_DTTong();
        } else {
            try {
                btnHienThiTatCaDanhMuc_DTTong.setText(HIEN_TAT_CA_TEXT);
                buttonStatusClose_DTTong();
                fillTableDTTong();
                fillBieuDo_DTTong();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnHienThiTatCaDanhMuc_DTTongActionPerformed

    private void btnHienThiTatCaDanhMuc_KhungGioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHienThiTatCaDanhMuc_KhungGioActionPerformed
        // TODO add your handling code here:
        if (btnHienThiTatCaDanhMuc_KhungGio.isSelected()) {
            btnHienThiTatCaDanhMuc_KhungGio.setText(HIEN_THEO_BO_LOC_TEXT);
            buttonStatusOpen_KhungGio();
        } else {
            try {
                btnHienThiTatCaDanhMuc_KhungGio.setText(HIEN_TAT_CA_TEXT);
                buttonStatusClose_KhungGio();
                dcsTheoNgay_KhungGio.setDate(DateHelper.now());
                fillTableTKKhungGio();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnHienThiTatCaDanhMuc_KhungGioActionPerformed

    private void cboChonKhungGioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboChonKhungGioItemStateChanged
        try {
            // TODO add your handling code here:
            fillTableTKKhungGio();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_cboChonKhungGioItemStateChanged

    private void dcsTheoNgay_KhungGioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcsTheoNgay_KhungGioPropertyChange
        try {
            // TODO add your handling code here:
            fillTableTKKhungGio();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_dcsTheoNgay_KhungGioPropertyChange

    private void btnBieuDoNgay_DTTongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBieuDoNgay_DTTongActionPerformed
        // TODO add your handling code here:
        tabsTonghopthongke.setSelectedIndex(0);
    }//GEN-LAST:event_btnBieuDoNgay_DTTongActionPerformed

    private void btnDTTongMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDTTongMouseReleased
        // TODO add your handling code here:
        setClickedListener(evt);
    }//GEN-LAST:event_btnDTTongMouseReleased

    private void btnDTPhimMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDTPhimMouseReleased
        // TODO add your handling code here:
        setClickedListener(evt);
    }//GEN-LAST:event_btnDTPhimMouseReleased

    private void btnDTDoAnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDTDoAnMouseReleased
        // TODO add your handling code here:
        setClickedListener(evt);
    }//GEN-LAST:event_btnDTDoAnMouseReleased

    private void btnDTPhongVeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDTPhongVeMouseReleased
        // TODO add your handling code here:
        setClickedListener(evt);
    }//GEN-LAST:event_btnDTPhongVeMouseReleased

    private void btnDTLuongVeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDTLuongVeMouseReleased
        // TODO add your handling code here:
        setClickedListener(evt);
    }//GEN-LAST:event_btnDTLuongVeMouseReleased

    private void btnKhungGioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhungGioMouseReleased
        // TODO add your handling code here:
        setClickedListener(evt);
    }//GEN-LAST:event_btnKhungGioMouseReleased

    private void btnXemBieuDoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXemBieuDoMouseReleased
        // TODO add your handling code here:
        setClickedListener(evt);
    }//GEN-LAST:event_btnXemBieuDoMouseReleased

    private void btnBieuDoNgay_LuongVeDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBieuDoNgay_LuongVeDBActionPerformed
        // TODO add your handling code here:
        tabsTonghopthongke.setSelectedIndex(0);
    }//GEN-LAST:event_btnBieuDoNgay_LuongVeDBActionPerformed

    private void cboTheoNam_VeDaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTheoNam_VeDaBanActionPerformed
        // TODO add your handling code here:
        if (rdoTheoNam_VeDaBan.isSelected()) {
            try {
                fillTableLuongVe_TheoNam();
                fillBieuDo_LuongVeDaBan();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_cboTheoNam_VeDaBanActionPerformed

    private void rdoTheoNam_VeDaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTheoNam_VeDaBanActionPerformed
        // TODO add your handling code here:
        cboThangTheoThang_Vedaban.setEnabled(false);
        cboNamTheoThang_Vedaban.setEnabled(false);
        cboTheoNam_VeDaBan.setEnabled(true);
        dcsTheoNgay_LuongVeDB.setEnabled(false);
    }//GEN-LAST:event_rdoTheoNam_VeDaBanActionPerformed

    private void cboNamTheoThang_VedabanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamTheoThang_VedabanActionPerformed
        // TODO add your handling code here:
        if (rdoTheoThang_VeDaBan.isSelected()) {
            try {
                fillTableLuongVe_TheoThang();
                fillBieuDo_LuongVeDaBan();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_cboNamTheoThang_VedabanActionPerformed

    private void rdoTheoThang_VeDaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTheoThang_VeDaBanActionPerformed
        // TODO add your handling code here:
//        cboLuongVeNgay.setEnabled(false);
        cboThangTheoThang_Vedaban.setEnabled(true);
        cboNamTheoThang_Vedaban.setEnabled(true);
        cboTheoNam_VeDaBan.setEnabled(false);
        dcsTheoNgay_LuongVeDB.setEnabled(false);
    }//GEN-LAST:event_rdoTheoThang_VeDaBanActionPerformed

    private void cboThangTheoThang_VedabanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThangTheoThang_VedabanActionPerformed
        // TODO add your handling code here:
        if (rdoTheoThang_VeDaBan.isSelected()) {
            try {
                fillTableLuongVe_TheoThang();
                fillBieuDo_LuongVeDaBan();
            }catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_cboThangTheoThang_VedabanActionPerformed

    private void dcsTheoNgay_LuongVeDBPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcsTheoNgay_LuongVeDBPropertyChange
        // TODO add your handling code here:
        if (rdoTheoNgay_LuongVeDB.isSelected()) {
            try {
                fillTableLuongVe_TheoNgay();
                fillBieuDo_LuongVeDaBan();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_dcsTheoNgay_LuongVeDBPropertyChange

    private void rdoTheoNgay_LuongVeDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTheoNgay_LuongVeDBActionPerformed
        // TODO add your handling code here:
        dcsTheoNgay_LuongVeDB.setEnabled(true);
        cboThangTheoThang_Vedaban.setEnabled(false);
        cboNamTheoThang_Vedaban.setEnabled(false);
        cboTheoNam_VeDaBan.setEnabled(false);
    }//GEN-LAST:event_rdoTheoNgay_LuongVeDBActionPerformed

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
            java.util.logging.Logger.getLogger(TongHopThongKeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TongHopThongKeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TongHopThongKeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TongHopThongKeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TongHopThongKeFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgDoAn_SP;
    private javax.swing.ButtonGroup btgDoAn_TG;
    private javax.swing.ButtonGroup btgDoanhThuTong;
    private javax.swing.ButtonGroup btgLuongVe;
    private javax.swing.ButtonGroup btgPhim;
    private javax.swing.ButtonGroup btgPhongVe;
    private javax.swing.ButtonGroup btgThongKeKhungGio;
    private javax.swing.ButtonGroup btgVeDaBan;
    private javax.swing.JButton btnBieuDoNgay_DTPhim;
    private javax.swing.JButton btnBieuDoNgay_DTTong;
    private javax.swing.JButton btnBieuDoNgay_LuongVeDB;
    private javax.swing.JLabel btnDTDoAn;
    private javax.swing.JLabel btnDTLuongVe;
    private javax.swing.JLabel btnDTPhim;
    private javax.swing.JLabel btnDTPhongVe;
    private javax.swing.JLabel btnDTTong;
    private javax.swing.JToggleButton btnHienThiTatCaDanhMuc_DTDoAN;
    private javax.swing.JToggleButton btnHienThiTatCaDanhMuc_DTLuongVe;
    private javax.swing.JToggleButton btnHienThiTatCaDanhMuc_DTPhim;
    private javax.swing.JToggleButton btnHienThiTatCaDanhMuc_DTPhongVe;
    private javax.swing.JToggleButton btnHienThiTatCaDanhMuc_DTTong;
    private javax.swing.JToggleButton btnHienThiTatCaDanhMuc_KhungGio;
    private javax.swing.JLabel btnKhungGio;
    private javax.swing.JButton btnSLVeGiam;
    private javax.swing.JButton btnTop5DoanhThu;
    private javax.swing.JLabel btnXemBieuDo;
    private javax.swing.JButton btnXuatFileExcel_DTPhim;
    private javax.swing.JButton btnXuatFileExcel_DTPhongVe;
    private javax.swing.JButton btnXuatFileExcel_DTTong;
    private javax.swing.JButton btnXuatFileExcel_DoAnDaBan;
    private javax.swing.JButton btnXuatFileExcel_TKKhungGio;
    private javax.swing.JButton btnXuatFileExcel_VeDaBan;
    private javax.swing.JComboBox<String> cboChonKhungGio;
    private javax.swing.JComboBox<String> cboNamTheoThang_DTDoAn;
    private javax.swing.JComboBox<String> cboNamTheoThang_DTPhim;
    private javax.swing.JComboBox<String> cboNamTheoThang_DTPhongVe;
    private javax.swing.JComboBox<String> cboNamTheoThang_DTTong;
    private javax.swing.JComboBox<String> cboNamTheoThang_Vedaban;
    private javax.swing.JComboBox<String> cboThangTheoThang_DTDoAn;
    private javax.swing.JComboBox<String> cboThangTheoThang_DTPhim;
    private javax.swing.JComboBox<String> cboThangTheoThang_DTPhongVe;
    private javax.swing.JComboBox<String> cboThangTheoThang_DTTong;
    private javax.swing.JComboBox<String> cboThangTheoThang_Vedaban;
    private javax.swing.JComboBox<String> cboTheoNam_DTDoAN;
    private javax.swing.JComboBox<String> cboTheoNam_DTPhim;
    private javax.swing.JComboBox<String> cboTheoNam_DTPhongVe;
    private javax.swing.JComboBox<String> cboTheoNam_DTTong;
    private javax.swing.JComboBox<String> cboTheoNam_VeDaBan;
    private javax.swing.JComboBox<String> cboTheoSize;
    private javax.swing.JComboBox<String> cboTheoTenDoAn;
    private com.toedter.calendar.JDateChooser dcsNgay_DTPhongVe;
    private com.toedter.calendar.JDateChooser dcsTheoNgay_DTPhim;
    private com.toedter.calendar.JDateChooser dcsTheoNgay_DTTong;
    private com.toedter.calendar.JDateChooser dcsTheoNgay_KhungGio;
    private com.toedter.calendar.JDateChooser dcsTheoNgay_LuongVeDB;
    private com.toedter.calendar.JDateChooser dscTheoNgay_DTDoAn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lblBieuDoNgay2;
    private javax.swing.JLabel lblBieuDoNgay3;
    private javax.swing.JLabel lblBieuDoNgay4;
    private javax.swing.JLabel lblTongHopThongKe;
    private javax.swing.JLabel lblXuatFileExcel;
    private javax.swing.JLabel lblXuatFileExcel1;
    private javax.swing.JLabel lblXuatFileExcel2;
    private javax.swing.JLabel lblXuatFileExcel3;
    private javax.swing.JLabel lblXuatFileExcel4;
    private javax.swing.JLabel lblXuatFileExcel5;
    private javax.swing.JPanel pnl1;
    private javax.swing.JPanel pnl2;
    private javax.swing.JPanel pnl3;
    private javax.swing.JPanel pnl4;
    private javax.swing.JPanel pnl5;
    private javax.swing.JPanel pnl6;
    private javax.swing.JPanel pnlBD;
    private javax.swing.JPanel pnlBD_DTLuongVe;
    private javax.swing.JPanel pnlBD_DTPhim;
    private javax.swing.JPanel pnlBD_DTTong;
    private javax.swing.JPanel pnlBieuDo;
    private javax.swing.JPanel pnlControl;
    private javax.swing.JPanel pnlDA;
    private javax.swing.JPanel pnlDTDoAn;
    private javax.swing.JPanel pnlDTPhim;
    private javax.swing.JPanel pnlDTTong;
    private javax.swing.JPanel pnlKG;
    private javax.swing.JPanel pnlKhungGio;
    private javax.swing.JPanel pnlLV;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlPV;
    private javax.swing.JPanel pnlPhim;
    private javax.swing.JPanel pnlPhongVe;
    private javax.swing.JPanel pnlTong;
    private javax.swing.JPanel pnlVeTheoNgay;
    private javax.swing.JRadioButton rdoTheoNam_DTDoAn;
    private javax.swing.JRadioButton rdoTheoNam_DTPhim;
    private javax.swing.JRadioButton rdoTheoNam_DTPhongVe;
    private javax.swing.JRadioButton rdoTheoNam_DTTong;
    private javax.swing.JRadioButton rdoTheoNam_VeDaBan;
    private javax.swing.JRadioButton rdoTheoNgay_DTDoAn;
    private javax.swing.JRadioButton rdoTheoNgay_DTPhim;
    private javax.swing.JRadioButton rdoTheoNgay_DTPhongVe;
    private javax.swing.JRadioButton rdoTheoNgay_DTTong;
    private javax.swing.JRadioButton rdoTheoNgay_LuongVeDB;
    private javax.swing.JRadioButton rdoTheoSize;
    private javax.swing.JRadioButton rdoTheoTenDoAn;
    private javax.swing.JRadioButton rdoTheoThang_DTDoAn;
    private javax.swing.JRadioButton rdoTheoThang_DTPhim;
    private javax.swing.JRadioButton rdoTheoThang_DTPhongVe;
    private javax.swing.JRadioButton rdoTheoThang_DTTong;
    private javax.swing.JRadioButton rdoTheoThang_VeDaBan;
    private javax.swing.JTabbedPane tabsTonghopthongke;
    private javax.swing.JTable tblDTPhim;
    private javax.swing.JTable tblDTPhongVe;
    private javax.swing.JTable tblDoanhThuDoAn;
    private javax.swing.JTable tblDoanhThuTong;
    private javax.swing.JTable tblLuongVeDaBan;
    private javax.swing.JTable tblThongKeKhungGio;
    // End of variables declaration//GEN-END:variables
}
