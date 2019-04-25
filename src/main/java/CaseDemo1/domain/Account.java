package CaseDemo1.domain;

/**
 * @ProjectName: Test1
 * @Package: CaseDemo1.service.domian
 * @ClassName: Account
 * @Description: 账户实体类
 * @Author: zengyanbo
 * @CreateDate: 2019-04-23 17:32
 * @UpdateDate: 2019-04-23 17:32
 * @Version: 1.0
 */

public class Account {
    private Integer id;
    private String name;
    private float money;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }
}
