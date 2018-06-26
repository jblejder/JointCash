package com.projectblejder.jointcash.infrastructure.converters

import com.projectblejder.jointcash.infrastructure.models.ExpenseType.Debt
import com.projectblejder.jointcash.infrastructure.models.ExpenseType.Pay
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ExpenseTypeConverterTest {

    lateinit var converter: ExpenseTypeConverter

    @Test
    fun `convert pay type`() {
        val stringValue = converter.fromTypeToString(Pay)
        val converted = converter.fromStringToType(stringValue)

        Assert.assertEquals("pay", stringValue)
        Assert.assertEquals(Pay, converted)
    }

    @Test
    fun `convert debt type`() {
        val stringValue = converter.fromTypeToString(Debt)
        val converted = converter.fromStringToType(stringValue)

        Assert.assertEquals("debt", stringValue)
        Assert.assertEquals(Debt, converted)
    }

    @Test(expected = IllegalStateException::class)
    fun `throws exception when string is not recognised`() {
        converter.fromStringToType("not recognised")
    }

    @Before
    fun before() {
        converter = ExpenseTypeConverter()
    }
}
