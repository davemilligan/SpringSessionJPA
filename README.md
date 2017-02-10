# Spring Session JPA
Spring Session を JPAとともに使うためのコードです。
利用する場合は、SessionEntityクラスとSessionEntityRepositoryクラスをコンポーネントスキャンされる場所に配置してください。
他のクラスはクラスパスが通っている任意の場所に配置してください。
そして、Configurationクラスに@EnableJPAHttpSessionをつけてください。例えばこんな感じです。
```
package org.my;

import org.springframework.context.annotation.Configuration;
import com.ukiuni.spring.session.jpa.EnableJPAHttpSession;

@Configuration
@EnableJPAHttpSession
public class JPASessionConfig {
}
```

# Apology
本当は独立ライブラリとして作成したかったのですが、
独立させるとSessionEntityRepositoryをJPAのRepositoryとして認識させられませんでした。
正確に言うと@EnableJpaRepositoriesを指定することによって認識させられはするのですが、
これをすると、他のパッケージのRepositoryを認識しなくなるため、独立させられませんでした。
お手数ですが、使用する場合はソースファイルを自らのアプリに取り込んでください。
※ 他に影響を与えずにRepositoryを作成する方法があれば教えてください。

# License
MIT