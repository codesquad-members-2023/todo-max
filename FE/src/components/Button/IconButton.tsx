import { CloseIcon } from "./CloseIcon";
import { EditIcon } from "./EditIcon";
import { HistoryIcon } from "./HistoryIcon";
import { PlusIcon } from "./PlusIcon";

type Props = {
  type: "close" | "edit" | "plus" | "history";
  width: string;
  height: string;
  color: string;
  onClick?(): void;
};

export function IconButton({ type, width, height, color, onClick }: Props) {
  return (
    <button
      onClick={onClick}
      css={{
        padding: "0px",
        display: "flex",
        width: width,
        height: height,
        justifyContent: "center",
        alignItems: "center",
        backgroundColor: "transparent",
        border: "none",
      }}>
      {type === "close" && (
        <CloseIcon width={width} height={height} color={color} />
      )}
      {type === "edit" && (
        <EditIcon width={width} height={height} color={color} />
      )}
      {type === "history" && (
        <HistoryIcon width={width} height={height} color={color} />
      )}
      {type === "plus" && (
        <PlusIcon width={width} height={height} color={color} />
      )}
    </button>
  );
}
