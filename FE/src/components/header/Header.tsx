import { useContext, useEffect, useState } from "react";
import { colors } from "../../constants/colors";
import { HistoryIcon } from "../History/HistoryIcon";
import { HistoryLayer } from "../History/HistoryLayer";
import { Txt } from "../Txt";
import { keyframes } from "@emotion/react";
import { ModalContext } from "../../contexts/ModalContext";

export function Header() {
  const [isHistoryLayerOpen, setIsHistoryLayerOpen] = useState(false);
  // const [historyData, setHistoryData] = useState<HistoryItemData[]>([]);
  const { historyData, setHistoryData } = useContext(ModalContext)!;
  const [historyAnimation, setHistoryAnimation] = useState(slideInAnimation);
  const [isHistoryAnimationEnd, setIsHistoryAnimationEnd] = useState(false);

  const handleClickHistoryIcon = () => {
    setIsHistoryLayerOpen(true);
    fetchHistoryData();
  };

  const handleClickCloseButton = () => {
    setHistoryAnimation(slideOutAnimation);
  };

  const handleAnimationEnd = () => {
    if (historyAnimation === slideOutAnimation) {
      setIsHistoryAnimationEnd(true);
    }
  };

  const fetchHistoryData = async () => {
    try {
      const response = await fetch("/history");
      const data = await response.json();
      setHistoryData(data);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    if (isHistoryAnimationEnd) {
      setIsHistoryLayerOpen(false);
      setIsHistoryAnimationEnd(false);
      setHistoryAnimation(slideInAnimation);
    }
  }, [isHistoryAnimationEnd]);

  return (
    <div
      css={{
        display: "flex",
        alignItems: "center",
        justifyContent: "space-between",
        width: "1280px",
        height: "28px",
        padding: "0 80px",
        margin: "18px 0",
        overflow: "hidden",
      }}>
      <Txt typography="displayBold24" color={`${colors.textStrong}`}>
        TODO LIST
      </Txt>
      <HistoryIcon onClick={handleClickHistoryIcon} />
      {isHistoryLayerOpen ? (
        <HistoryLayer
          onAnimationEnd={handleAnimationEnd}
          animation={historyAnimation}
          onClickCloseButton={handleClickCloseButton}
          historyData={historyData}
        />
      ) : null}
    </div>
  );
}

export type HistoryItemData = {
  title: string;
  from?: string;
  to?: string;
  at: string;
  action: string;
};
const slideIn = keyframes`
  0% {
    transform: translateX(200%);
  }
  100% {
    transform: translateX(298px);
  }
`;

const slideOut = keyframes`
  0% {
    transform: translateX(298px);
  }
  100% {
    transform: translateX(200%);
  }
`;

const slideInAnimation = {
  animation: `${slideIn} 0.5s ease-out forwards`,
};

const slideOutAnimation = {
  animation: `${slideOut} 0.5s ease-out forwards`,
};
