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

import java.util.Comparator;
import java.util.List;

/** A hand of cards dealt to the player. */
public class Hand implements Comparable<Hand> {

  private final List<Card> cards;
  private final HandRank rank;

  private Hand(List<Card> cards, HandRank rank) {
    this.cards = List.copyOf(cards);
    this.rank = rank;
  }

  /** Create a hand of cards and work out what rank it has. */
  static Hand create(List<Card> cards) {
    for (HandRank handRank : HandRank.values()) {
      List<Card> highestCards = handRank.highCardsIfMatching(cards);
      if (!highestCards.isEmpty()) {
        return new Hand(cards, handRank);
      }
    }
    throw new IllegalArgumentException("Failed to match rank for this hand");
  }

  /** Create an empty hand with a specific rank (for testing). */
  static Hand create(HandRank handRank) {
    return new Hand(List.of(), handRank);
  }

  HandRank getRank() {
    return rank;
  }

  List<Card> getCards() {
    return cards;
  }

  @Override
  public int compareTo(Hand o) {
    if(rank.compareTo(o.rank) == 0){
      List<Card> p1 = rank.highCardsIfMatching(cards);
      List<Card> p2 = o.rank.highCardsIfMatching(o.cards);
      for(int i = 0; i < p1.size(); i++){
        int tmp = p1.get(i).getValue().compareTo(p2.get(i).getValue());
        if(tmp == 0) continue;
        return tmp;
      }
      return 0;
    }
    return -rank.compareTo(o.rank);
  }
}
