import { colors } from "../../constants/colors";
import { IconButton } from "../Button/IconButton";
import { Txt } from "../Txt";
import { Badge } from "./Badge";

export function ColumnTitle({
  columnTitle,
  cardsCount,
  showAddCard,
}: {
  columnTitle: string;
  cardsCount: number;
  showAddCard(): void;
}) {
  return (
    <div css={ColumnTitleContainer}>
      <div css={ColumnTitleTextArea}>
        <Txt typography="displayBold16" color={`${colors.textBold}`}>
          {columnTitle}
        </Txt>
        <Badge cardsCount={cardsCount} />
      </div>
      <IconButton
        type="plus"
        width="24px"
        height="24px"
        color={colors.textWeak}
        onClick={showAddCard}
      />
      <IconButton
        type="close"
        width="24px"
        height="24px"
        color={colors.textWeak}
      />
    </div>
  );
}

const ColumnTitleContainer = {
  display: "flex",
  width: "100%",
  height: "24px",
  gap: "8px",
  alignContent: "center",
};

const ColumnTitleTextArea = {
  display: "flex",
  width: "204px",
  justifyContent: "start",
  alignContent: "center",
  gap: "8px",
};
