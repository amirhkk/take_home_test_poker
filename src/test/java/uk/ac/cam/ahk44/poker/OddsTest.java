/*
 * Copyright 2022 Andrew Rice <acr31@cam.ac.uk>, Amir Kadkhodaei
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.ac.cam.ahk44.poker;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class OddsTest {

  @Test
  public void odds_includesAllHandKinds() {
    // ARRANGE
    Odds odds = new Odds();

    // ACT
    List<Count> tabulation = odds.tabulateByRank();

    // ASSERT
    assertThat(tabulation)
        .containsExactly(
            new Count(HandRank.ROYAL_FLUSH, 0, 0),
            new Count(HandRank.STRAIGHT_FLUSH, 0, 0),
            new Count(HandRank.FOUR_OF_A_KIND, 0, 0),
            new Count(HandRank.FULL_HOUSE, 0, 0),
            new Count(HandRank.FLUSH, 0, 0),
            new Count(HandRank.STRAIGHT, 0, 0),
            new Count(HandRank.THREE_OF_A_KIND, 0, 0),
            new Count(HandRank.TWO_PAIR, 0, 0),
            new Count(HandRank.ONE_PAIR, 0, 0),
            new Count(HandRank.HIGH_CARD, 0, 0))
        .inOrder();
  }

  @Test
  public void odds_countsDealtHands() {
    // ARRANGE
    Hand hand1 = Hand.create(HandRank.HIGH_CARD);
    Hand hand2 = Hand.create(HandRank.ROYAL_FLUSH);
    Odds odds = new Odds();

    // ACT
    odds.dealt(hand2);
    odds.dealt(hand1);
    List<Count> tabulation = odds.tabulateByRank();

    // ASSERT
    assertThat(tabulation)
        .containsAtLeast(new Count(HandRank.ROYAL_FLUSH, 0.5, 0), new Count(HandRank.HIGH_CARD, 0.5, 0));
  }

  @Test
  public void odds_countsWonHands() {
    // ARRANGE
    Hand hand1 = Hand.create(HandRank.HIGH_CARD);
    Hand hand2 = Hand.create(HandRank.ROYAL_FLUSH);
    Odds odds = new Odds();

    // ACT
    odds.won(hand2);
    odds.dealt(hand2);
    odds.won(hand1);
    odds.dealt(hand1);
    odds.dealt(hand1);
    odds.dealt(hand1);
    odds.dealt(hand1);
    List<Count> tabulation = odds.tabulateByRank();

    // ASSERT
    assertThat(tabulation)
            .containsAtLeast(new Count(HandRank.ROYAL_FLUSH, 0.2, 1), new Count(HandRank.HIGH_CARD, 0.8, 0.25));
  }
}
