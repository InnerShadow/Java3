package rfe.bsu.SikolenkoMa.laba3;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
public class GornerTableCellRenderer implements TableCellRenderer {
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();
    private double BeginOfDioposone = 0.f;
    private double EndOfDioposone = 0.f;
    int clue = 0;
    boolean IsPrevFirstColumBlack = true;
    private String needle = null;
    private DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance();

    public GornerTableCellRenderer() {
// Показывать только 5 знаков после запятой
        formatter.setMaximumFractionDigits(5);
// Не использовать группировку (т.е. не отделять тысячи
// ни запятыми, ни пробелами), т.е. показывать число как "1000",
// а не "1 000" или "1,000"
        formatter.setGroupingUsed(false);
// Установить в качестве разделителя дробной части точку, а не
// запятую. По умолчанию, в региональных настройках
// Россия/Беларусь дробная часть отделяется запятой
        DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);
// Разместить надпись внутри панели

        panel.add(label);
// Установить выравнивание надписи по левому краю панели
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

    }

    public void setBeginOfDioposone(double beginOfDioposone) {
        BeginOfDioposone = beginOfDioposone;
        
    }

    public void setEndOfDioposone(double endOfDioposone) {
        EndOfDioposone = endOfDioposone;
    }





    public Component getTableCellRendererComponent(JTable table,
                                                   Object value, boolean isSelected, boolean hasFocus, int row, int col) {

// Преобразовать double в строку с помощью форматировщика
        String formattedDouble = formatter.format(value);
// Установить текст надписи равным строковому представлению числа
        label.setText(formattedDouble);


        if (col == 1 && needle!=null && needle.equals(formattedDouble)) {
// Номер столбца = 1 (т.е. второй столбец) + иголка не null
// (значит что-то ищем) +
// значение иголки совпадает со значением ячейки таблицы -
// окрасить задний фон панели в красный цвет
            panel.setBackground(Color.RED);
            clue++;
        } else {

            double copearValue = Double.valueOf(label.getText());
            if(copearValue < EndOfDioposone && copearValue > BeginOfDioposone){
                panel.setBackground(Color.ORANGE);
                clue++;
                return panel;
            }

            boolean c = (clue % 4) == 0;
            if(c){
                if(IsPrevFirstColumBlack){
                    label.setForeground(Color.BLACK);
                    panel.setBackground(Color.WHITE);
                    IsPrevFirstColumBlack = false;
                } else {
                    label.setForeground(Color.WHITE);
                    panel.setBackground(Color.BLACK);
                    IsPrevFirstColumBlack = true;
                }
                clue++;
                return panel;
            }

            boolean b = (clue % 2) == 0;
            if(b){
                if(IsPrevFirstColumBlack){
                    label.setForeground(Color.WHITE);
                    panel.setBackground(Color.BLACK);
                } else {
                    label.setForeground(Color.BLACK);
                    panel.setBackground(Color.WHITE);
                }

            } else {
                if(IsPrevFirstColumBlack){
                    label.setForeground(Color.BLACK);
                    panel.setBackground(Color.WHITE);
                } else {
                    label.setForeground(Color.WHITE);
                    panel.setBackground(Color.BLACK);
                }
            }
            clue++;
        }

        return panel;
    }
    public void setNeedle(String needle) {
        this.needle = needle;
    }
}

