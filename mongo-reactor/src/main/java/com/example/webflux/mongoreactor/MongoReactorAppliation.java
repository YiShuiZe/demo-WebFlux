package com.example.webflux.mongoreactor;

import com.example.webflux.mongoreactor.model.MyEvent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;

/**
 * <h3>WebFlux</h3>
 * <p>${description}</p>
 * Created by yang on 20-8-30 下午1:15
 * updated by yang on 20-8-30 下午1:15
 */
@SpringBootApplication
public class MongoReactorAppliation {
    public static void main(String[] args) {
        SpringApplication.run(MongoReactorAppliation.class, args);
    }

    /**
     * @Tailable仅支持有大小限制的（“capped”）collection，而自动创建的collection是不限制大小的，
     * 因此我们需要先手动创建。Spring Boot提供的CommandLineRunner可以帮助我们实现这一点。
     * @param mongo
     * @return
     */
    @Bean//对于复杂的Bean只能通过Java Config的方式配置，这也是为什么Spring3之后官方推荐这种配置方式的原因，这段代码可以放到配置类中，本例我们就直接放到启动类WebFluxDemoApplication了；
    public CommandLineRunner initData(ReactiveMongoOperations mongo) {//MongoOperations提供对MongoDB的操作方法，由Spring注入的mongo实例已经配置好，直接使用即可；
        return (String... args) -> {//CommandLineRunner也是一个函数式接口，其实例可以用lambda表达；
            // 没生效？？下面操作是异步的，指不定那个先执行，需要先让删除执行，阻塞等它完成，再执行创建，也需要阻塞等待完成？？因为是在其他线程执行的！！！。
            mongo.dropCollection(MyEvent.class).block();//如果有，先删除collection，生产环境慎用这种操作；
            mongo.createCollection(MyEvent.class,
                    CollectionOptions.empty().maxDocuments(200).size(100000).capped()).block();//创建一个记录个数为10的capped的collection，容量满了之后，新增的记录会覆盖最旧的。
        };
    }

    /**
     * 1. OK，这个时候我们请求一下http://localhost:8080/events，发现立马返回了，并没有挂起。原因在于collection中一条记录都没有，而@Tailable起作用的前提是至少有一条记录。
     * 2. 跑一下WebClient测试程序插入5条数据，然后再次请求：
     * 3. 请求是挂起的，这没错，但是只有两条数据，看WebClient测试程序的控制台明明发出了5个请求啊。
     * 4. 原因定义的CollectionOptions.empty().size(200).capped()中，size指的是以字节为单位的大小，并且会向上取到256的整倍数，所以我们刚才定义的是256byte大小的collection，所以最多容纳两条记录。我们可以这样改一下：
     * 5. CollectionOptions.empty().maxDocuments(200).size(100000).capped()
     * 6. maxDocuments限制了记录条数，size限制容量且是必须定义的，因为MongoDB不像关系型数据库有严格的列和字段大小定义，鬼知道会存多大的数据进来，所以容量限制是必要的。
     * 7. 好了，再次启动应用，先插入5条数据，然后请求/events，收到5条记录后请求仍然挂起，在插入5条数据，curl客户端又会陆续收到新的数据。
     */
}
