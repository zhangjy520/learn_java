package cc.gukeer.common;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
public class TransWordToHtml {
    public static void main(String[] args) {
        ActiveXComponent app = new ActiveXComponent("Word.Application");
        app.setProperty("Visible", new Variant(false));
        Dispatch doc1 = app.getProperty("Documents").toDispatch();
        //打开aaaa.doc  
        Dispatch doc2 = Dispatch.invoke(
                doc1,
                "Open",
                Dispatch.Method,
                new Object[]{"e://last//asbc_a_001.docx", new Variant(false), new Variant(true)},
                new int[1]
        ).toDispatch();
        //另存为aaaa.html  
        Dispatch.invoke(
                doc2,
                "SaveAs",
                Dispatch.Method,
                new Object[]{
                        "e://res.txt",
                        new Variant(8)//7为txt格式， 8保存为html格式
                },
                new int[1]
        );
        Variant f = new Variant(false);
        Dispatch.call(doc2, "Close", f);
    }
}  