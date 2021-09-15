package bowling.domain.frame;

import bowling.exception.BowlingFrameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalFrameTest {
    @Test
    void create() {
        Frame frame = new FinalFrame();
        assertThat(frame).isEqualTo(new FinalFrame());
    }

    @DisplayName("두번 연속 miss 인 경우 3번째 투구 시도시 에러 발생")
    @Test
    void miss_error() {
        Frame frame = new FinalFrame().next(1).next(2);
        assertThatThrownBy(() -> frame.next(3)).isInstanceOf(BowlingFrameException.class);
    }

    @DisplayName("spare 인 경우 3번째 투구를 한다.")
    @Test
    void thirdTurn_spare() {
        Frame frame = new FinalFrame().next(1).next(9).next(3);
        assertThat(frame.bonusFirstCount()).isEqualTo(3);
    }

    @DisplayName("strike 인 경우 3번째 투구를 한다.")
    @Test
    void thirdTurn_strike() {
        Frame frame = new FinalFrame().next(10).next(2).next(3);
        assertThat(frame.bonusFirstCount()).isEqualTo(2);
        assertThat(frame.bonusSecondCount()).isEqualTo(3);
    }

    @DisplayName("4번째 투구 시도시 에러 발생")
    @Test
    void forthTurn_error() {
        Frame frame = new FinalFrame().next(10).next(2).next(3);
        assertThatThrownBy(() -> frame.next(4)).isInstanceOf(BowlingFrameException.class);
    }
}