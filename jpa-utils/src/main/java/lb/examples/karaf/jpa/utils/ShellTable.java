package lb.examples.karaf.jpa.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;

/**
 *
 */
public class ShellTable
{
    public static final int MAX_COL_SIZE = 56;

    private List<String> header = new ArrayList<String>();
    private List<List<String>> content = new ArrayList<List<String>>();
    private int maxColSize = MAX_COL_SIZE;

    /**
     * c-tor
     */
    public ShellTable() {
    }

    /**
     * c-tor
     *
     * @param args
     */
    public ShellTable(String... args) {
        for(String s : args) {
            header.add(s);
        }
    }

    /**
     *
     * @param max
     */
    public void setMxColSize(int max) {
        maxColSize = max;
    }

    /**
     *
     * @param args
     */
    public void setHeader(String... args) {
        for(String s : args) {
            header.add(s);
        }
    }

    /**
     *
     * @param arg
     */
    public void addHeader(String arg) {
        header.add(arg);
    }

    /**
     *
     * @return
     */
    public List<String> addRow() {
        List<String> row = new ArrayList<String>();
        content.add(row);

        return row;
    }

    /**
     * @param args
     */
    public void addRow(Object... args) {
        List<String> row = addRow();
        for(Object arg : args) {
            row.add(ObjectUtils.toString(arg));
        }
    }

    /**
     *
     */
    public void print() {
        int[] sizes = new int[header.size()];
        updateSizes(sizes, header);
        for(List<String> row : content) {
            updateSizes(sizes,row);
        }

        String headerLine = getRow(sizes, header," | ");
        System.out.println(headerLine);
        System.out.println(underline(headerLine.length()));
        for(List<String> row : content) {
            System.out.println(getRow(sizes,row," | "));
        }
    }
    
    /**
     * 
     */
    public void clear() {
        content.clear();
    }

    /**
     *
     * @param length
     * @return
     */
    private String underline(int length) {
        char[] exmarks = new char[length];
        Arrays.fill(exmarks,'-');

        return new String(exmarks);
    }

    /**
     *
     * @param sizes
     * @param row
     * @param separator
     * @return
     */
    private String getRow(int[] sizes,List<String> row,String separator) {
        StringBuilder line = new StringBuilder();
        int c = 0;
        for(String cell : row) {
            if(cell == null) {
                cell = "";
            }

            if(cell.length() > maxColSize) {
                cell = cell.substring(0, maxColSize - 1);
            }

            cell = cell.replaceAll("\n","");
            line.append(String.format("%-" + sizes[c] + "s",cell));
            if(c + 1 < row.size()) {
                line.append(separator);
            }

            c++;
        }

        return line.toString();
    }

    /**
     *
     * @param sizes
     * @param row
     */
    private void updateSizes(int[] sizes,List<String> row) {
        int c = 0;
        for(String cellContent : row) {
            int cellSize = cellContent != null ? cellContent.length() : 0;
            cellSize = Math.min(cellSize, maxColSize);
            if(cellSize > sizes[c]) {
                sizes[c] = cellSize;
            }

            c++;
        }
    }
}
