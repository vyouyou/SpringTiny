package util;

import com.qiyouyou.domain.util.CastUtil;
import org.junit.Assert;
import org.junit.Test;

public class CastUtilTest {

    @Test
    public void doubleCastTest(){
        String str = "3.14";
        System.out.print("doubleCastTest"+CastUtil.castDouble(str));
        Assert.assertEquals(CastUtil.castDouble(str), 3.14,0);
    }
}
