package lotto;

import java.util.ArrayList;
import lotto.model.LottoGenerator;
import lotto.model.WinningNumber;
import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class LottoTest{

    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    // 아래에 추가 테스트 작성 가능
    @DisplayName("당첨 번호 및 보너스 번호를 저장한다.")
    @Test
    void saveWinningNumber() {
        WinningNumber winningNumber = new WinningNumber();
        String inputWinningNumber = "1,40,27,10,3,33";
        String inputBonusNumber = "12";

        List<Integer> testWinningNumber = List.of(1, 3, 10, 27, 33, 40);
        int testBonusNumber = 12;

        winningNumber.setWinningNumbers(inputWinningNumber);
        winningNumber.setBonusNumber(inputBonusNumber);

        assertEquals(winningNumber.getWinningNumbers(), testWinningNumber);
        assertEquals(winningNumber.getBonusNumber(), testBonusNumber);

    }

    @DisplayName("6개의 랜덤 로또 번호 생성 및 저장")
    @Test
    void generateLotto() {
        String money = "4000";
        LottoGenerator lottoGenerator = new LottoGenerator();
        ArrayList<List<Integer>> lotteries;

        lottoGenerator.generateLotto(money);
        lotteries = lottoGenerator.getLotteries();

        assertEquals(lotteries.size(),4);

        for(int i=0; i<lotteries.size(); i++) {
            assertEquals(lotteries.get(i).size(), 6);
            assertThat(lotteries.get(i).stream().allMatch(v -> v >= 1 && v <= 45));
        }

        for(int i=1; i<lotteries.size(); i++) {
            assertNotEquals(lotteries.get(i), lotteries.get(i-1));
        }
    }
}