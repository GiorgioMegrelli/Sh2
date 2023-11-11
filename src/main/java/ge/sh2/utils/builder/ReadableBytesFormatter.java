package ge.sh2.utils.builder;

public class ReadableBytesFormatter {

    public enum OrderType {
        SIZE_SPACE_CHAR, CHAR_SPACE_SIZE;
    }

    public static final String SIZE_CHARS = "BKMGTPE";

    private static final double SIZE_IN_STEP = 1024.0;

    private String chars = SIZE_CHARS;
    private int floatingLen = 1;
    private String space = " ";
    private OrderType order = OrderType.SIZE_SPACE_CHAR;

    public void floatLength(int len) {
        this.floatingLen = len;
    }

    public void setCharacters(String chars) {
        this.chars = chars;
    }

    public void setSpaceBetween(String space) {
        this.space = space;
    }

    public void setOrder(OrderType order) {
        this.order = order;
    }

    public String format(long sizeArg) {
        double size = sizeArg * 1.0;
        int fmtIndex = 0;
        while(size >= SIZE_IN_STEP && fmtIndex < chars.length()) {
            size /= SIZE_IN_STEP;
            fmtIndex++;
        }

        String sizeStr = sizeToString(size);
        if(order == OrderType.CHAR_SPACE_SIZE) {
            return chars.charAt(fmtIndex) + space + sizeStr;
        } else if(order == OrderType.SIZE_SPACE_CHAR) {
            return sizeStr + space + chars.charAt(fmtIndex);
        }
        throw new RuntimeException("Invalid order type: " + order);
    }

    private String sizeToString(double size) {
        if(floatingLen == 0) {
            return String.valueOf(Math.round(size));
        }
        double withRound = floatingLen * 10.0;
        return String.valueOf(Math.round(size * withRound) / withRound);
    }

}
