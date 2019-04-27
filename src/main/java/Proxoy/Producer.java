package Proxoy;

/**
 * @ProjectName: aop
 * @Package: Proxoy
 * @ClassName: Producer
 * @Description:
 * @Author: zengyanbo
 * @CreateDate: 2019-04-26 11:45
 * @UpdateDate: 2019-04-26 11:45
 * @Version: 1.0
 */

public class Producer implements Iproduct {
    @Override
    public void sale(float money) {
        System.out.println("销售产品拿到钱："+money);
    }

    @Override
    public void afterService(float money) {
        System.out.println("售后服务拿到钱："+money);
    }
}
