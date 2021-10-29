DROP TABLE IF EXISTS card_expansion;
DROP TABLE IF EXISTS card_effect;
DROP TABLE IF EXISTS card;
DROP TABLE IF EXISTS effect;
DROP TABLE IF EXISTS expansion;


CREATE TABLE expansion(
        id serial primary key,
        name varchar(60) not null
);

CREATE TABLE card(
        id serial primary key,
        name varchar(60) not null,
        type varchar(60) not null,
        description varchar(300) not null,
        cost int not null,
        expansion_id bigint,
        
        constraint fk_expansion_id foreign key (expansion_id) references expansion(id)
);

CREATE TABLE effect(
        id serial primary key,
        name varchar(60) not null
);

CREATE TABLE card_effect(
        card_id bigint,
        effect_id bigint,
        
        constraint fk_card_id foreign key (card_id) references card(id),
        constraint fk_effect_id foreign key (effect_id) references effect(id),
        constraint pk_card_effect primary key (card_id, effect_id)
);

INSERT INTO expansion VALUES (default, 'Dominion Second Edition');
INSERT INTO expansion VALUES (default, 'Intrigue Second Edition');
INSERT INTO expansion VALUES (default, 'Prosperity');

INSERT INTO effect VALUES (default, 'Draw');
INSERT INTO effect VALUES (default, 'Trash');
INSERT INTO effect VALUES (default, '+2 Action');
INSERT INTO effect VALUES (default, 'Buy');
INSERT INTO effect VALUES (default, 'Gain');

-- Insert Dominion Second Edition 
INSERT INTO card VALUES (default, 'Cellar', 'Action', '+1 Action. Discard any number of cards, then draw that many.', 2, 1);
INSERT INTO card VALUES (default, 'Chapel', 'Action', 'Trash up to 4 cards from your hand.', 2, 1);
INSERT INTO card_effect VALUES (2, 2);
INSERT INTO card VALUES (default, 'Moat', 'Action - Reaction', '+2 Cards. When another player plays an Attack card, you may first reveal this from your hand, to be unaffected by it.', 2, 1);
INSERT INTO card_effect VALUES (3, 1);
INSERT INTO card VALUES (default, 'Harbinger', 'Action', '+1 Card +1 Action. Look through your discard pile. You may put a card from it onto your deck.', 3, 1);
INSERT INTO card VALUES (default, 'Merchant', 'Action', '+1 Card +1 Action. The first time you play a Silver this turn, +$1.', 3, 1);
INSERT INTO card VALUES (default, 'Vassal', 'Action', 'Discard the top card of your deck. If it is an Action card, you may play it.', 3, 1);
INSERT INTO card VALUES (default, 'Village', 'Action', '+1 Card +2 Actions.', 3, 1);
INSERT INTO card_effect VALUES(7, 3);
INSERT INTO card VALUES (default, 'Workshop', 'Action', 'Gain a card costing up to $4.', 3, 1);
INSERT INTO card_effect VALUES (8, 5);
INSERT INTO card VALUES (default, 'Bureaucrat', 'Action - Attack', 
'Gain a Silver card; put it on top of your deck. Each other player reveals a Victory card from his hand and puts it on his deck (or reveals a hand with no Victory cards).', 4, 1);
INSERT INTO card_effect VALUES (9, 5);
INSERT INTO card VALUES (default, 'Gardens', 'Victory', 'Worth 1 Victory for every 10 cards in your deck (rounded down).', 4, 1);
INSERT INTO card VALUES (default, 'Militia', 'Action - Attack', '+$2. Each other player discards down to 3 cards in their hand.', 4, 1);
INSERT INTO card VALUES (default, 'Moneylender', 'Action', 'You may trash a Copper from your hand. If you do, +$3.', 4, 1);
INSERT INTO card VALUES (default, 'Poacher', 'Action', '+1 Card +1 Action +$1. Discard a card per empty Supply pile.', 4, 1);
INSERT INTO card VALUES (default, 'Remodel', 'Action', 'Trash a card from your hand. Gain a card costing up to $2 more than the trashed card.', 4, 1);
INSERT INTO card_effect VALUES (14, 2);
INSERT INTO card_effect VALUES (14, 5);
INSERT INTO card VALUES (default, 'Smithy', 'Action', '+3 Cards.', 4, 1);
INSERT INTO card_effect VALUES (15, 1);
INSERT INTO card VALUES (default, 'Throne Room', 'Action', 'You may play an Action card from your hand twice.', 4, 1);
INSERT INTO card VALUES (default, 'Bandit', 'Action - Attack', 
'Gain a Gold. Each other player reveals the top two cards of their deck, trashes a revealed Treasure other than Copper, and discards the rest. ', 5, 1);
INSERT INTO card_effect VALUES (17, 5);
INSERT INTO card VALUES (default, 'Council Room', 'Action', '+4 Cards +1 Buy. Each other player draws a card.', 5, 1);
INSERT INTO card_effect VALUES (18, 1);
INSERT INTO card_effect VALUES (18, 4);
INSERT INTO card VALUES (default, 'Festival', 'Action', '+2 Actions + 1 Buy + $2.', 5, 1);
INSERT INTO card_effect VALUES (19, 3);
INSERT INTO card_effect VALUES (19, 4);
INSERT INTO card VALUES (default, 'Laboratory', 'Action', '+2 Cards +1 Action.', 5, 1);
INSERT INTO card_effect VALUES (20, 1);
INSERT INTO card VALUES (default, 'Library', 'Action', 
'Draw until you have 7 cards in hand, skipping any Action cards you choose to; set those aside, discarding them afterwards.', 5, 1);
INSERT INTO card_effect VALUES (21, 1);
INSERT INTO card VALUES (default, 'Market', 'Action', '+1 Card +1 Action +1 Buy +$1.', 5, 1);
INSERT INTO card_effect VALUES (22, 4);
INSERT INTO card VALUES (default, 'Mine', 'Action', 'You may trash a Treasure from your hand. Gain a Treasure to your hand costing up to $3 more than it.', 5, 1);
INSERT INTO card_effect VALUES (23, 5);
INSERT INTO card VALUES (default, 'Sentry', 'Action', 
'+1 Card +1 Action. Look at the top 2 cards of your deck. Trash and/or discard any number of them. Put the rest back on top in any order.', 5, 1);
INSERT INTO card_effect VALUES (24, 2);
INSERT INTO card VALUES (default, 'Witch', 'Action - Attack', '+2 Cards. Each other player gains a Curse card.', 5, 1);
INSERT INTO card_effect VALUES (25, 1);
INSERT INTO card VALUES (default, 'Artisan', 'Action', 'Gain a card to your hand costing up to $5. Put a card from your hand onto your deck.', 6, 1);
INSERT INTO card_effect VALUES (26, 5);

-- Insert Intrigue Second Edition
INSERT INTO card VALUES (default, 'Courtyard', 'Action', '+3 Cards. Put a card from your hand onto your deck.', 2, 2);
INSERT INTO card_effect VALUES (27, 1);
INSERT INTO card VALUES (default, 'Lurker', 'Action', '+1 Action. Choose one: Trash an Action card from the Supply; or gain an Action card from the trash.', 2, 2);
INSERT INTO card VALUES (default, 'Pawn', 'Action', 'Choose two: +1 Card; +1 Action; +1 Buy; +$1. The choices must be different.', 2, 2);
INSERT INTO card_effect VALUES (29, 4);
INSERT INTO card VALUES (default, 'Masquerade', 'Action', 
'+2 Cards. Each player with any cards in hand passes one to the next such player to their left, at once. Then you may trash a card from your hand.', 3, 2);
INSERT INTO card_effect VALUES (30, 1);
INSERT INTO card VALUES (default, 'Shanty Town', 'Action', '+2 Actions. Reveal your hand. If you have no Action cards in hand, +2 Cards.', 3, 2);
INSERT INTO card_effect VALUES (31, 3);
INSERT INTO card VALUES (default, 'Swindler', 'Action - Attack', 
'+$2 Each other player trashes the top card of their deck and gains a card with the same cost that you choose.', 3, 2);
INSERT INTO card VALUES (default, 'Wishing Well', 'Action', '+1 Card +1 Action. Name a card, then reveal the top card of your deck. If you name it, put it in your hand.', 3, 2);
INSERT INTO card VALUES (default, 'Baron', 'Action', '+1 Buy. You may discard an Estate for +$4. If you do not, gain an Estate.', 4, 2);
INSERT INTO card_effect VALUES (34, 4);
INSERT INTO card VALUES (default, 'Bridge', 'Action', '+1 Buy +$1. This turn, cards (everywhere) cost $1 less, but not less than $0.', 4, 2);
INSERT INTO card_effect VALUES (35, 4);
INSERT INTO card VALUES (default, 'Conspirator', 'Action', '+$2. If you have played 3 or more Actions this turn (counting this), +1 Card and +1 Action.', 4, 2);
INSERT INTO card VALUES (default, 'Diplomat', 'Action - Reaction', '+2 Cards. If you have 5 or fewer cards in hand (after drawing), +2 Actions. 
When another player plays an Attack card, you may reveal this from a hand of 5 or more cards, to draw 2 cards then discard 3.', 4, 2);
INSERT INTO card_effect VALUES (37, 1);
INSERT INTO card VALUES (default, 'Ironworks', 'Action', 
'Gain a card costing up to $4. If the gained card is an... Action card, +1 Action. Treasure card, +$1. Victory card, +1 Card.', 4, 2);
INSERT INTO card_effect VALUES (38, 5);
INSERT INTO card VALUES (default, 'Mill', 'Action - Victory', '+1 Card +1 Action. You may discard 2 cards, for +$2. 1 Victory Point.', 4, 2);
INSERT INTO card VALUES (default, 'Mining Village', 'Action', '+1 Card +2 Actions. You may trash this for +$2.', 4, 2);
INSERT INTO card_effect VALUES (40, 3);
INSERT INTO card VALUES (default, 'Secret Passage', 'Action', '+2 Cards +1 Action. Take a card from your hand put it anywhere in your deck.', 4, 2);
INSERT INTO card_effect VALUES (41, 1);
INSERT INTO card VALUES (default, 'Steward', 'Action', 'Choose one: +2 Cards; or +$2; or trash 2 cards from your hand.', 3, 2);
INSERT INTO card_effect VALUES (42, 1);
INSERT INTO card_effect VALUES (42, 2);
INSERT INTO card VALUES (default, 'Courtier', 'Action', 
'Reveal a card from your hand. For each type it has (Action, Attack, etc.), choose one: +1 Action; or +1 Buy; or +$3; or gain a Gold. The choices must be different.', 5, 2);
INSERT INTO card_effect VALUES (43, 4);
INSERT INTO card_effect VALUES (43, 5);
INSERT INTO card VALUES (default, 'Duke', 'Victory', 'Worth 1 Victory Point per Duchy you have.', 5, 2);
INSERT INTO card VALUES (default, 'Minion', 'Action - Attack', 
'Choose one: +$2; or discard your hand, +4 Cards, and each other player with at least 5 cards in hand discards their hand and draws 4 cards.', 5, 2);
INSERT INTO card VALUES (default, 'Patrol', 'Action', 
'+3 Cards. Reveal the top 4 cards of your deck. Put the Victory cards and Curses into your hand. Put the rest back in any order.', 5, 2);
INSERT INTO card_effect VALUES (46, 1);
INSERT INTO card VALUES (default, 'Replace', 'Action - Attack', 'Trash a card from your hand. Gain a card costing up to $2 more than it. 
If the gained card is an Action or Treasure, put it onto your deck; if it is a Victory card, each other player gains a Curse.', 5, 2);
INSERT INTO card_effect VALUES (47, 2);
INSERT INTO card_effect VALUES (47, 5);
INSERT INTO card VALUES (default, 'Torturer', 'Action - Attack', 
'+3 Cards. Each other player either discards 2 cards or gains a Curse to their hand, their choice. (They may pick an option they cannot do.', 5, 2);
INSERT INTO card_effect VALUES (48, 1);
INSERT INTO card VALUES (default, 'Trading Post', 'Action', 'Trash 2 cards from your hand. If you did, gain a Silver to your hand.', 5, 2);
INSERT INTO card_effect VALUES (49, 2);
INSERT INTO card VALUES (default, 'Upgrade', 'Action', '+1 Card +1 Action. Trash a card from your hand. Gain a card costing exactly $1 more than it.', 5, 2);
INSERT INTO card_effect VALUES (50, 2);
INSERT INTO card_effect VALUES (50, 5);
INSERT INTO card VALUES (default, 'Harem', 'Treasure - Victory', '$2. 2 Victory Points', 6, 2);
INSERT INTO card VALUES (default, 'Nobles', 'Action - Victory', 'Choose one: +3 Cards; or +2 Actions. 2 Victory Points.', 6, 2);
INSERT INTO card_effect VALUES (52, 1);
INSERT INTO card_effect VALUES (52, 3);

-- Insert Seaside
-- Insert Alchemy
-- Insert Prosperity
-- Insert Cornucopia
-- Insert Hinterlands
-- Insert Dark Ages
-- Insert Guilds
-- Insert Adventures
-- Insert Empires
-- Insert Nocturne
-- Insert Renaissance
-- Insert Menagerie

