package woowacourse.shopping.di.module

import android.content.Context
import androidx.room.Room
import com.now.di.Module
import woowacourse.shopping.data.CartDatabase
import woowacourse.shopping.data.CartProductDao
import woowacourse.shopping.data.DatabaseCartRepository
import woowacourse.shopping.data.DefaultProductRepository
import woowacourse.shopping.data.InMemoryCartRepository
import woowacourse.shopping.di.annotation.Database
import woowacourse.shopping.di.annotation.InMemory
import woowacourse.shopping.repository.CartRepository
import woowacourse.shopping.repository.ProductRepository

class DefaultModule(private val context: Context) : Module {

    fun cartProductDao(): CartProductDao {
        val database = Room
            .databaseBuilder(context, CartDatabase::class.java, "kkrong-database")
            .build()
        return database.cartProductDao()
    }

    @InMemory
    fun inMemoryCartRepository(): CartRepository {
        return InMemoryCartRepository()
    }

    @Database
    fun databaseCartRepository(cartProductDao: CartProductDao): CartRepository {
        return DatabaseCartRepository(cartProductDao)
    }

    fun productRepository(): ProductRepository {
        return DefaultProductRepository()
    }
}
