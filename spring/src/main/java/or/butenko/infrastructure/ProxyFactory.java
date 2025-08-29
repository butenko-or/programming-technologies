package or.butenko.infrastructure;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

//TODO: дописать создание Proxy.
public class ProxyFactory {

  public static <T> T createProxy(final T target) {

    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(target.getClass());
    enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
          long start = System.currentTimeMillis();
          try {
            return proxy.invoke(target, args);
          } finally {
            long duration = System.currentTimeMillis() - start;
            System.out.printf("Method %s executed in %d ms%n", method.getName(), duration);
          }
        }
    );
    return (T) enhancer.create();
  }

}
