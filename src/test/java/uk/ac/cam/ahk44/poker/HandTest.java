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
import static uk.ac.cam.ahk44.poker.Card.Suit.CLUBS;
import static uk.ac.cam.ahk44.poker.Card.Suit.DIAMONDS;
import static uk.ac.cam.ahk44.poker.Card.Suit.HEARTS;
import static uk.ac.cam.ahk44.poker.Card.Suit.SPADES;
import static uk.ac.cam.ahk44.poker.Card.Value.FIVE;
import static uk.ac.cam.ahk44.poker.Card.Value.FOUR;
import static uk.ac.cam.ahk44.poker.Card.Value.SIX;
import static uk.ac.cam.ahk44.poker.Card.Value.THREE;
import static uk.ac.cam.ahk44.poker.Card.Value.TWO;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class HandTest {

  @Test
  public void createHand_classifiesRank_withStraight() {
    // ARRANGE
    List<Card> straight =
        List.of(
            new Card(DIAMONDS, TWO),
            new Card(HEARTS, THREE),
            new Card(CLUBS, FOUR),
            new Card(SPADES, FIVE),
            new Card(SPADES, SIX));

    // ACT
    Hand hand = Hand.create(straight);

    // ASSERT
    assertThat(hand.getRank()).isEqualTo(HandRank.STRAIGHT);
  }
}
