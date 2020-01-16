package com.jc.performancetests;

import com.jc.performancetests.beancopier.BeanCopierConverter;
import com.jc.performancetests.dozer.DozerConverter;
import com.jc.performancetests.fastjson.FastjsonConverter;
import com.jc.performancetests.gson.GsonConverter;
import com.jc.performancetests.hardcode.HardcodeConverter;
import com.jc.performancetests.jackson.JacksonConverter;
import com.jc.performancetests.jmapper.JMapperConverter;
import com.jc.performancetests.mapstruct.MapStructConverter;
import com.jc.performancetests.model.destination.DestinationCode;
import com.jc.performancetests.model.destination.Order;
import com.jc.performancetests.model.source.AccountStatus;
import com.jc.performancetests.model.source.Address;
import com.jc.performancetests.model.source.DeliveryData;
import com.jc.performancetests.model.source.Discount;
import com.jc.performancetests.model.source.OrderStatus;
import com.jc.performancetests.model.source.PaymentType;
import com.jc.performancetests.model.source.Product;
import com.jc.performancetests.model.source.RefundPolicy;
import com.jc.performancetests.model.source.Review;
import com.jc.performancetests.model.source.Shop;
import com.jc.performancetests.model.source.SourceCode;
import com.jc.performancetests.model.source.SourceOrder;
import com.jc.performancetests.model.source.User;
import com.jc.performancetests.modelmapper.ModelMapperConverter;
import com.jc.performancetests.orika.OrikaConverter;
import com.jc.performancetests.spring.BeanUtilsConverter;
import org.modelmapper.internal.util.Assert;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @author ChenJunChang
 */
@Fork(value = 1, warmups = 1)
@Warmup(iterations = 1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode({Mode.Throughput, Mode.SingleShotTime})
@Measurement(iterations = 1)
@State(Scope.Group)
public class MappingFrameworksPerformance {
    private static final OrikaConverter ORIKA_CONVERTER = new OrikaConverter();
    private static final JMapperConverter JMAPPER_CONVERTER = new JMapperConverter();
    private static final ModelMapperConverter MODEL_MAPPER_CONVERTER = new ModelMapperConverter();
    private static final DozerConverter DOZER_CONVERTER = new DozerConverter();
    private static final FastjsonConverter FASTJSON_CONVERTER = new FastjsonConverter();
    private static final GsonConverter GSON_CONVERTER = new GsonConverter();
    private static final JacksonConverter JACKSON_CONVERTER = new JacksonConverter();
    private static final BeanUtilsConverter BEAN_UTIL_CONVERTER = new BeanUtilsConverter();
    private static final MapStructConverter MAP_STRUCT_CONVERTER = MapStructConverter.MAPPER;
    private static final BeanCopierConverter BEAN_COPIER_CONVERTER = new BeanCopierConverter();
    private static final HardcodeConverter HARDCODE_CONVERTER = new HardcodeConverter();
    private static SourceOrder sourceOrder = null;
    private static SourceCode sourceCode = null;

    public static void main(String[] args) throws IOException, RunnerException {
        org.openjdk.jmh.Main.main(args);
    }

    @Setup
    public static void setUp() {
        User user = new User("John", "John@doe.com", AccountStatus.ACTIVE);
        RefundPolicy refundPolicy = new RefundPolicy(true, 30, Collections
                .singletonList("Refundable only if not used!"));

        Product product = new Product("Sample Product to be sold", true, BigDecimal.valueOf(10.99),
                100,
                "Sample Product",
                refundPolicy
        );

        Discount discount = new Discount(Instant.now().toString(), Instant.now().toString(), BigDecimal.valueOf(5.99));
        Address deliveryAddress = new Address("Washington Street 5", "New York", "55045", "USA");
        DeliveryData deliveryData = new DeliveryData(deliveryAddress, true, "", 10);
        Address shopAddress = new Address("Roosvelt Street 9", "Boston", "55042", "USA");
        User reviewingUser = new User("John", "Johhny@John.com", AccountStatus.ACTIVE);
        User negativeReviewingUser = new User("Carl", "Carl@Coral.com", AccountStatus.ACTIVE);
        Review review = new Review(5, 5, 5, reviewingUser, "The best shop I've ever bought things in");

        Review negativeReview = new Review(1, 1, 1, negativeReviewingUser, "I will never buy anything again here!");

        List<Review> reviewList = new ArrayList<>();
        reviewList.add(review);
        reviewList.add(negativeReview);
        Shop shop = new Shop("Super Shop", shopAddress, "www.super-shop.com", reviewList);
        sourceOrder = new SourceOrder(
                Instant.MAX.toString(),
                PaymentType.TRANSFER,
                discount,
                deliveryData,
                user,
                Collections.singletonList(product),
                shop,
                1,
                OrderStatus.CONFIRMED,
                Instant.now().toString()
        );


        sourceCode = new SourceCode("This is source code!");

        Order order1 = ORIKA_CONVERTER.convert(sourceOrder);
        Order order2 = MODEL_MAPPER_CONVERTER.convert(sourceOrder);
        Order order3 = MODEL_MAPPER_CONVERTER.convert(sourceOrder);
        Order order4 = DOZER_CONVERTER.convert(sourceOrder);
        Order order5 = FASTJSON_CONVERTER.convert(sourceOrder);
        Order order6 = GSON_CONVERTER.convert(sourceOrder);
        Order order7 = MAP_STRUCT_CONVERTER.convert(sourceOrder);
        Order order8 = BEAN_UTIL_CONVERTER.convert(sourceOrder);
        Order order9 = BEAN_COPIER_CONVERTER.convert(sourceOrder);
        Order order10 = JACKSON_CONVERTER.convert(sourceOrder);
        Order order11 = HARDCODE_CONVERTER.convert(sourceOrder);

        Assert.isTrue(order1.hashCode() == order2.hashCode());
        Assert.isTrue(order2.hashCode() == order3.hashCode());
        Assert.isTrue(order3.hashCode() == order4.hashCode());
        Assert.isTrue(order4.hashCode() == order5.hashCode());
        Assert.isTrue(order5.hashCode() == order6.hashCode());
        Assert.isTrue(order6.hashCode() == order7.hashCode());
        Assert.isTrue(order7.hashCode() == order8.hashCode());
        Assert.isTrue(order8.hashCode() == order9.hashCode());
        Assert.isTrue(order9.hashCode() == order10.hashCode());
        Assert.isTrue(order10.hashCode() == order11.hashCode());
    }

    @Benchmark
    @Group("realLifeTest")
    public Order orikaMapperRealLifeBenchmark() {
        return ORIKA_CONVERTER.convert(sourceOrder);
    }

    @Benchmark
    @Group("realLifeTest")
    public Order jmapperRealLifeBenchmark() {
        return JMAPPER_CONVERTER.convert(sourceOrder);
    }

    @Benchmark
    @Group("realLifeTest")
    public Order modelMapperRealLifeBenchmark() {
        return MODEL_MAPPER_CONVERTER.convert(sourceOrder);
    }

    @Benchmark
    @Group("realLifeTest")
    public Order dozerMapperRealLifeBenchmark() {
        return DOZER_CONVERTER.convert(sourceOrder);
    }

    @Benchmark
    @Group("realLifeTest")
    public Order fastjsonMapperRealLifeBenchmark() {
        return FASTJSON_CONVERTER.convert(sourceOrder);
    }

    @Benchmark
    @Group("realLifeTest")
    public Order gsonMapperRealLifeBenchmark() {
        return GSON_CONVERTER.convert(sourceOrder);
    }

    @Benchmark
    @Group("realLifeTest")
    public Order beanUtilsRealLifeBenchmark() {
        return BEAN_UTIL_CONVERTER.convert(sourceOrder);
    }

    @Benchmark
    @Group("realLifeTest")
    public Order mapStructRealLifeBenchmark() {
        return MAP_STRUCT_CONVERTER.convert(sourceOrder);
    }

    @Benchmark
    @Group("realLifeTest")
    public Order beanCopierRealLifeBenchmark() {
        return BEAN_COPIER_CONVERTER.convert(sourceOrder);
    }

    @Benchmark
    @Group("realLifeTest")
    public Order jacksonRealLifeBenchmark() {
        return JACKSON_CONVERTER.convert(sourceOrder);
    }

    @Benchmark
    @Group("realLifeTest")
    public Order hardcodeRealLifeBenchmark() {
        return HARDCODE_CONVERTER.convert(sourceOrder);
    }


    @Benchmark
    @Group("simpleTest")
    public DestinationCode orikaMapperSimpleBenchmark() {
        return ORIKA_CONVERTER.convert(sourceCode);
    }

    @Benchmark
    @Group("simpleTest")
    public DestinationCode jmapperSimpleBenchmark() {
        return JMAPPER_CONVERTER.convert(sourceCode);
    }

    @Benchmark
    @Group("simpleTest")
    public DestinationCode modelMapperSimpleBenchmark() {
        return MODEL_MAPPER_CONVERTER.convert(sourceCode);
    }

    @Benchmark
    @Group("simpleTest")
    public DestinationCode dozerMapperSimpleBenchmark() {
        return DOZER_CONVERTER.convert(sourceCode);
    }

    @Benchmark
    @Group("simpleTest")
    public DestinationCode fastjsonMapperSimpleBenchmark() {
        return FASTJSON_CONVERTER.convert(sourceCode);
    }

    @Benchmark
    @Group("simpleTest")
    public DestinationCode gsonMapperSimpleBenchmark() {
        return GSON_CONVERTER.convert(sourceCode);
    }

    @Benchmark
    @Group("simpleTest")
    public DestinationCode beanUtilsSimpleBenchmark() {
        return BEAN_UTIL_CONVERTER.convert(sourceCode);
    }

    @Benchmark
    @Group("simpleTest")
    public DestinationCode mapStructMapperSimpleBenchmark() {
        return MAP_STRUCT_CONVERTER.convert(sourceCode);
    }

    @Benchmark
    @Group("simpleTest")
    public DestinationCode beanCopierSimpleBenchmark() {
        return BEAN_COPIER_CONVERTER.convert(sourceCode);
    }

    @Benchmark
    @Group("simpleTest")
    public DestinationCode jacksonSimpleBenchmark() {
        return JACKSON_CONVERTER.convert(sourceCode);
    }

    @Benchmark
    @Group("simpleTest")
    public DestinationCode hardcodeSimpleBenchmark() {
        return HARDCODE_CONVERTER.convert(sourceCode);
    }

}
