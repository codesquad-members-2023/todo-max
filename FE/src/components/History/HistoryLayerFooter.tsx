import { useContext } from "react";
import { colors } from "../../constants/colors";
import { Txt } from "../Txt";
import { ModalContext } from "../../contexts/ModalContext";

export function HistoryLayerFooter() {
  const modalContextValue = useContext(ModalContext);
  const { setIsAlertOpen, setAlertType } = modalContextValue!;

  const handleClickDeleteAllButton = () => {
    setAlertType("removeHistory");
    setIsAlertOpen(true);
  };

  return (
    <div
      className="HistoryLayerFooter"
      css={{
        display: "flex",
        justifyContent: "flex-end",
        alignItems: "center",
        width: "330px",
        height: "48px",
        padding: "0 20px 0 0",
      }}>
      <div css={{ cursor: "pointer" }} onClick={handleClickDeleteAllButton}>
        <Txt typography="displayBold14" color={colors.textDanger}>
          기록 전체 삭제
        </Txt>
      </div>
    </div>
  );
}
