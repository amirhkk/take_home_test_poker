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

import java.util.List;
import java.util.Arrays;

/** Static class for running the simulation. */
class Simulator {

  /** Simulate dealing a hand from the deck and record the outcome. */
  static List<Count> simulate(Deck deck, int rounds) {
    Odds odds = new Odds();

    for (int i = 0; i < rounds; i++) {
      deck.shuffle();
      Hand[] hands = new Hand[5];
      for(int j = 0; j < 5; j++){
        hands[j] = deck.deal();
        odds.dealt(hands[j]);
      }
      Arrays.sort(hands);
      if(hands[4].compareTo(hands[3]) > 1) odds.won(hands[4]);
    }
    return odds.tabulateByRank();
  }
}
