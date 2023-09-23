package woowacourse.shopping.di.module

import android.content.Context
import androidx.room.Room
import com.now.di.Module
import woowacourse.shopping.data.CartDatabase
import woowacourse.shopping.data.CartProductDao
import woowacourse.shopping.data.DatabaseCartRepository
import woowacourse.shopping.data.InMemoryCartRepository
import woowacourse.shopping.di.annotation.Database
import woowacourse.shopping.di.annotation.InMemory
import woowacourse.shopping.repository.CartRepository

class ApplicationModule(private val context: Context) : Module {

    fun provideCartProductDao(): CartProductDao {
        val database = Room
            .databaseBuilder(context, CartDatabase::class.java, "krrong-database")
            .build()
        return database.cartProductDao()
    }

    @InMemory
    fun provideInMemoryCartRepository(): CartRepository {
        return InMemoryCartRepository()
    }

    @Database
    fun provideDatabaseCartRepository(cartProductDao: CartProductDao): CartRepository {
        return DatabaseCartRepository(cartProductDao)
    }
}
