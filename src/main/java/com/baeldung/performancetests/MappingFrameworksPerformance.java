package com.baeldung.performancetests;

import com.baeldung.performancetests.beancopier.BeanCopierConverter;
import com.baeldung.performancetests.dozer.DozerConverter;
import com.baeldung.performancetests.fastjson.FastjsonConverter;
import com.baeldung.performancetests.gson.GsonConverter;
import com.baeldung.performancetests.jmapper.JMapperConverter;
import com.baeldung.performancetests.mapstruct.MapStructConverter;
import com.baeldung.performancetests.model.destination.DestinationCode;
import com.baeldung.performancetests.model.destination.Order;
import com.baeldung.performancetests.model.source.AccountStatus;
import com.baeldung.performancetests.model.source.Address;
import com.baeldung.performancetests.model.source.DeliveryData;
import com.baeldung.performancetests.model.source.Discount;
import com.baeldung.performancetests.model.source.OrderStatus;
import com.baeldung.performancetests.model.source.PaymentType;
import com.baeldung.performancetests.model.source.Product;
import com.baeldung.performancetests.model.source.RefundPolicy;
import com.baeldung.performancetests.model.source.Review;
import com.baeldung.performancetests.model.source.Shop;
import com.baeldung.performancetests.model.source.SourceCode;
import com.baeldung.performancetests.model.source.SourceOrder;
import com.baeldung.performancetests.model.source.User;
import com.baeldung.performancetests.modelmapper.ModelMapperConverter;
import com.baeldung.performancetests.orika.OrikaConverter;
import com.baeldung.performancetests.spring.BeanUtilsConverter;
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
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.All)
@Measurement(iterations = 1)
@State(Scope.Group)
public class MappingFrameworksPerformance {
    private static final OrikaConverter ORIKA_CONVERTER = new OrikaConverter();
    private static final JMapperConverter JMAPPER_CONVERTER = new JMapperConverter();
    private static final ModelMapperConverter MODEL_MAPPER_CONVERTER = new ModelMapperConverter();
    private static final DozerConverter DOZER_CONVERTER = new DozerConverter();
    private static final FastjsonConverter FASTJSON_CONVERTER = new FastjsonConverter();
    private static final GsonConverter GSON_CONVERTER = new GsonConverter();
    private static final BeanUtilsConverter BEAN_UTIL_CONVERTER = new BeanUtilsConverter();
    private static final MapStructConverter MAP_STRUCT_CONVERTER = MapStructConverter.MAPPER;
    private static final BeanCopierConverter BEAN_COPIER_CONVERTER = new BeanCopierConverter();
    private static SourceOrder sourceOrder = null;
    private static SourceCode sourceCode = null;

    public static void main(String[] args) throws IOException, RunnerException {
//        setUp();
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

        Assert.isTrue(order1.hashCode() == order2.hashCode());
        Assert.isTrue(order2.hashCode() == order3.hashCode());
        Assert.isTrue(order3.hashCode() == order4.hashCode());
        Assert.isTrue(order4.hashCode() == order5.hashCode());
        Assert.isTrue(order5.hashCode() == order6.hashCode());
        Assert.isTrue(order6.hashCode() == order7.hashCode());
        Assert.isTrue(order7.hashCode() == order8.hashCode());
        Assert.isTrue(order8.hashCode() == order9.hashCode());
    }

    @Benchmark
    @Group("realLifeTest")
    public Order orikaMapper_RealLifeBenchmark() {
        return ORIKA_CONVERTER.convert(sourceOrder);
    }

    @Benchmark
    @Group("realLifeTest")
    public Order jmapper_RealLifeBenchmark() {
        return JMAPPER_CONVERTER.convert(sourceOrder);
    }

    @Benchmark
    @Group("realLifeTest")
    public Order modelMapper_RealLifeBenchmark() {
        return MODEL_MAPPER_CONVERTER.convert(sourceOrder);
    }

    @Benchmark
    @Group("realLifeTest")
    public Order dozerMapper_RealLifeBenchmark() {
        return DOZER_CONVERTER.convert(sourceOrder);
    }

    @Benchmark
    @Group("realLifeTest")
    public Order fastjsonMapper_RealLifeBenchmark() {
        return FASTJSON_CONVERTER.convert(sourceOrder);
    }

    @Benchmark
    @Group("realLifeTest")
    public Order gsonMapper_RealLifeBenchmark() {
        return GSON_CONVERTER.convert(sourceOrder);
    }

    @Benchmark
    @Group("realLifeTest")
    public Order beanUtils_RealLifeBenchmark() {
        return BEAN_UTIL_CONVERTER.convert(sourceOrder);
    }

    @Benchmark
    @Group("realLifeTest")
    public Order mapStruct_RealLifeBenchmark() {
        return MAP_STRUCT_CONVERTER.convert(sourceOrder);
    }

    @Benchmark
    @Group("realLifeTest")
    public Order beanCopier_RealLifeBenchmark() {
        return BEAN_COPIER_CONVERTER.convert(sourceOrder);
    }


    @Benchmark
    @Group("simpleTest")
    public DestinationCode orikaMapper_SimpleBenchmark() {
        return ORIKA_CONVERTER.convert(sourceCode);
    }

    @Benchmark
    @Group("simpleTest")
    public DestinationCode jmapper_SimpleBenchmark() {
        return JMAPPER_CONVERTER.convert(sourceCode);
    }

    @Benchmark
    @Group("simpleTest")
    public DestinationCode modelMapper_SimpleBenchmark() {
        return MODEL_MAPPER_CONVERTER.convert(sourceCode);
    }

    @Benchmark
    @Group("simpleTest")
    public DestinationCode dozerMapper_SimpleBenchmark() {
        return DOZER_CONVERTER.convert(sourceCode);
    }

    @Benchmark
    @Group("simpleTest")
    public DestinationCode fastjsonMapper_SimpleBenchmark() {
        return FASTJSON_CONVERTER.convert(sourceCode);
    }

    @Benchmark
    @Group("simpleTest")
    public DestinationCode gsonMapper_SimpleBenchmark() {
        return GSON_CONVERTER.convert(sourceCode);
    }

    @Benchmark
    @Group("simpleTest")
    public DestinationCode beanUtils_SimpleBenchmark() {
        return BEAN_UTIL_CONVERTER.convert(sourceCode);
    }

    @Benchmark
    @Group("simpleTest")
    public DestinationCode mapStructMapper_SimpleBenchmark() {
        return MAP_STRUCT_CONVERTER.convert(sourceCode);
    }

    @Benchmark
    @Group("simpleTest")
    public DestinationCode beanCopier_SimpleBenchmark() {
        return BEAN_COPIER_CONVERTER.convert(sourceCode);
    }

}
