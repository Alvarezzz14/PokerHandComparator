package com.poker.pokerhandcomparator.CartaUtilsTest;

import com.poker.pokerhandcomparator.utils.CartaUtils;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class CartaUtilsTest {

    @Test
    public void testConvertirValorAEntero_ValoresCorrectos() {
        assertThat(CartaUtils.convertirValorAEntero("A")).isEqualTo(14);
        assertThat(CartaUtils.convertirValorAEntero("K")).isEqualTo(13);
        assertThat(CartaUtils.convertirValorAEntero("Q")).isEqualTo(12);
        assertThat(CartaUtils.convertirValorAEntero("J")).isEqualTo(11);
        assertThat(CartaUtils.convertirValorAEntero("10")).isEqualTo(10);
    }
}
