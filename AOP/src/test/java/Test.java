import com.yc.test.AppConfig;
import com.yc.test.biz.HelloBizImpl;
import lombok.experimental.ExtensionMethod;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtensionMethod(SpringExtension.class) //jupiter整合 spring的方案
@ContextConfiguration( classes = AppConfig.class)
public class Test {

    private HelloBizImpl biz = new HelloBizImpl();

    @org.junit.Test
    public void test(){
        biz.showAll();
    }
}
