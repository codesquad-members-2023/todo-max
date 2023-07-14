import { useContext, useState } from "react";
import { Card, Column } from "../../pages/MainPage";
import { DefaultCard } from "../Card/DefaultCard";
import { ColumnTitle } from "./ColumnTitle";
import { AddCard } from "../Card/AddCard";
import { ModalContext } from "../../contexts/ModalContext";

export function ColumnWrapper({ column }: { column: Column }) {
  const { setHistoryData } = useContext(ModalContext)!;
  const [isAddCard, setIsAddCard] = useState<boolean>(false);
  const [cardsList, setCardsList] = useState<Card[]>(column.cards); // column.cards
  const columnTitle = column.name;

  // const cardsCount = 10; // 두자리 수 모킹 값
  const showAddCard = () => {
    setIsAddCard(true);
  };

  const closeAddCard = () => {
    setIsAddCard(false);
  };

  const addNewCard = (inputTitle: string, inputContent: string) => {
    const newCard = {
      id: Date.now(),
      title: inputTitle,
      contents: inputContent,
    };

    const newHistory = {
      title: inputTitle,
      at: columnTitle,
      action: "생성",
    };

    setHistoryData((prevHistoryData) => [newHistory, ...prevHistoryData]);
    setCardsList((prevCardsList) => [newCard, ...prevCardsList]);
    closeAddCard();
  };

  const removeCard = (key: number, cardTitle: string) => {
    const filter = cardsList.filter((list) => list.id !== key);
    const newHistory = {
      title: cardTitle,
      at: columnTitle,
      action: "삭제",
    };

    setHistoryData((prevHistoryData) => [newHistory, ...prevHistoryData]);
    setCardsList(filter);
  };

  return (
    <div
      css={{
        display: "flex",
        flexDirection: "column",
        width: "268px",
        gap: "8px",
        padding: "0 16px",
      }}>
      <ColumnTitle
        columnTitle={columnTitle}
        cardsCount={cardsList.length}
        showAddCard={showAddCard}
      />
      {isAddCard && (
        <AddCard closeAddCard={closeAddCard} addNewCard={addNewCard} />
      )}
      {cardsList.map((card) => (
        <DefaultCard
          key={card.id}
          id={card.id}
          cardTitle={card.title}
          cardContent={card.contents}
          removeCard={removeCard}
        />
      ))}
    </div>
  );
}
