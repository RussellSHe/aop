package Proxoy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ProjectName: aop
 * @Package: Proxoy
 * @ClassName: Client
 * @Description:
 * @Author: zengyanbo
 * @CreateDate: 2019-04-26 13:31
 * @UpdateDate: 2019-04-26 13:31
 * @Version: 1.0
 */

public class Client {
    public static void main(String[] args) {
        final Producer producer = new Producer();

        Iproduct proxyProducer = (Iproduct)Proxy.newProxyInstance(producer.getClass().getClassLoader(), producer.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result=null;
                Float money=(float)args[0];
                if("sale".equals(method.getName())){

                result=method.invoke(producer, money*0.8f);
                }
                return result;
            }
        });

        proxyProducer.sale(10000f);


    }
}
