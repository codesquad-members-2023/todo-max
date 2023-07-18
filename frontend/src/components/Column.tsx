import React, { useState } from "react";
import { styled } from "styled-components";
import Card from "./Card/Card.tsx";
import NewCard from "./Card/NewCard.tsx";
import IconButton from "./common/IconButton.tsx";
import addButtonIcon from "../assets/plus.svg";
import deleteButtonIcon from "../assets/closed.svg";
import { CardType } from "../types.ts";

export default function Column({
  name,
  cards,
  currMouseCoords,
  dragCardId,
  addNewCardHandler,
  editCardHandler,
  deleteCardHandler,
  updateMouseCoordsHandler,
  dragCardIdHandler,
}: {
  name: string;
  cards: CardType[];
  currMouseCoords: [number, number];
  dragCardId: number | null;
  addNewCardHandler: (card: CardType) => void;
  editCardHandler: (card: CardType) => void;
  deleteCardHandler: (deletedCardInfo: {
    id: number;
    columnId: number;
  }) => void;
  updateMouseCoordsHandler: (x: number, y: number) => void;
  dragCardIdHandler: (cardId: number | null) => void;
}) {
  const [isNewCardActive, setIsNewCardActive] = useState(false);

  const toggleNewCard = () => {
    setIsNewCardActive(!isNewCardActive);
  };

  return (
    <StyledColumn>
      <Header>
        <div className="column-info-container">
          <h2>{name}</h2>
          <span>{cards.length < 100 ? cards.length : "99+"}</span>
        </div>
        <div className="buttons-container">
          <IconButton
            className="add-button"
            src={addButtonIcon}
            alt="카드 추가"
            onClick={toggleNewCard}
          />
          <IconButton
            className="delete-button"
            src={deleteButtonIcon}
            alt="칼럼 삭제"
          />
        </div>
      </Header>

      <ul className="cards-list">
        {isNewCardActive && (
          <NewCard {...{ toggleNewCard, addNewCardHandler }} />
        )}

        {cards.map((cardDetails) => (
          <Card
            {...{
              key: cardDetails.id,
              cardDetails,
              currMouseCoords,
              dragCardId,
              editCardHandler,
              deleteCardHandler,
              updateMouseCoordsHandler,
              dragCardIdHandler,
            }}
          />
        ))}
      </ul>
    </StyledColumn>
  );
}

const StyledColumn = styled.div`
  width: 300px;
  display: flex;
  flex-direction: column;

  .cards-list {
    width: inherit;
    display: flex;
    flex-direction: column;
    flex-grow: 1;
    gap: 10px;
    overflow-y: auto;
  }
`;

const Header = styled.header`
  width: 100%;
  padding: 0 16px 10px;
  display: flex;
  justify-content: space-between;

  .column-info-container {
    display: flex;
    align-items: center;
    gap: 8px;

    h2 {
      color: ${({ theme: { colors } }) => colors.grey700};
      font: ${({ theme: { font } }) => font.displayBold16};
    }

    span {
      width: 24px;
      height: 24px;
      display: flex;
      justify-content: center;
      align-items: center;
      border: 1px solid ${({ theme: { colors } }) => colors.grey200};
      border-radius: ${({ theme: { objectStyles } }) => objectStyles.radius.s};
      color: ${({ theme: { colors } }) => colors.grey500};
      font: ${({ theme: { font } }) => font.displayMD12};
    }
  }

  .buttons-container {
    display: flex;
    align-items: center;
    gap: 8px;
  }
`;
