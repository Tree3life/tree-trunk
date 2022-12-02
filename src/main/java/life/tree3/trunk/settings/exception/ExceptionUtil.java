package life.tree3.trunk.settings.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <p>描述: 异常工具类 </p>
 * <p>创建时间: 2022/12/2 10:57 </p>
 */
public class ExceptionUtil {
    /**
     * 将堆栈信息 以 字符串形式 输出
     */
    public static String getMessage(Throwable e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            // 将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }
}
