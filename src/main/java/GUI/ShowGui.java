package GUI;

import BeanNovel.Chapter_info;
import BeanNovel.Novel_info;
import NovelDao.NovelInfoDao;
import SpiderNovel.SpiderChapter_Info;
import SpiderNovel.SpiderCommon;
import SpiderNovel.SpiderNovel_Info;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ShowGui {
    private JLabel jlabel;
    private JButton btn_spider;
    private JButton btn_showInfo;
    private JButton btn_download;
    private JTextField jtf;
    private JTextArea jta;
    private JTable jtable;
    private DefaultTableModel model;
    Novel_info Ninfo;
    Chapter_info Cinfo;

    ShowGui(){
        init();
    }

    public void init() {
        String img_url = "C:\\Users\\JIAS\\Desktop\\JavaProject\\SpiderNovel\\src\\main\\icon.jpg";
        final JFrame frame = new JFrame("笔趣阁网络小说抓取"); //建立一个窗口
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(img_url));
        frame.setLayout(new FlowLayout());//修改布局管理

        //final JPanel panel = new JPanel();

        jlabel = new JLabel("网址：");
        frame.add(jlabel);
        jtf = new JTextField();
        jtf.setPreferredSize(new Dimension (250, 25));
        jtf.setFont(new Font("黑体", Font.PLAIN, 14));
        jtf.setText("http://www.shuquge.com/txt/98111/");
        frame.add(jtf);
        btn_spider = new JButton("抓取");
        frame.add(btn_spider);//按钮上的文字
        btn_spider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String url = jtf.getText();
                    System.out.println(url);
                    Ninfo = SpiderNovel_Info.writeNovelInfo(url);
                    System.out.println(Ninfo.toString());
                    int id = NovelInfoDao.selectInfo_id(Ninfo.getName());
                    System.out.println(Ninfo.getName());
                    System.out.println(id);
                    Cinfo = SpiderChapter_Info.writeChaptersInfo(url, id);
                    jta.setText("写入数据库成功");
                } catch (Exception e1) {

                }
            }
        });


        btn_showInfo = new JButton("显示");//创建一个按钮
        frame.add(btn_showInfo);
        btn_download = new JButton("下载");//创建一个按钮
        frame.add(btn_download);


        jta = new JTextArea( 30, 45);
        jta.setLineWrap(true);
        jta.setEditable(true);
        frame.add(jta);


        btn_showInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ninfo = NovelInfoDao.selectInfo();
                jta.setFont(new Font("黑体", Font.PLAIN, 16));
                jta.setText(Ninfo.toString());
            }
        });

        btn_download.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println(Ninfo.toString());
                    //JOptionPane.showMessageDialog(new Panel(), "后台下载，请稍后！", "提示",JOptionPane.WARNING_MESSAGE);
                    //System.out.println(Ninfo.toString());
                    boolean isWrite = SpiderCommon.write2txt(Ninfo, Cinfo);
                    System.out.println("下载到本地成功");
                    if(isWrite){
                        JOptionPane.showMessageDialog(new Panel(), "下载完成！", "提示",JOptionPane.WARNING_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(new Panel(), "请重新抓取", "提示",JOptionPane.WARNING_MESSAGE);
                    }
                    JFileChooser chooser = new JFileChooser();
                    //打开选择器面板
                    //chooser.showOpenDialog(new JPanel());
                } catch (Exception e1) {

                }
            }
        });


        frame.setSize(520, 600); //设置窗口的大小
        frame.setResizable(false);//设置窗口不可改变大小
        frame.setLocation(500, 150);//设置窗口的初始位置
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口
        frame.setVisible(true); //显示窗口
    }

    public static void main(String[] args) {
        ShowGui sg = new ShowGui();
    }

}


