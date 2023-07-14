import { buttonOpacity } from "../../constants/opacity";
import { Txt } from "../Txt";

type Props = {
  text: string;
  width: string;
  height: string;
  color: string;
  backgroundColor: string;
  onClick?(): void;
  disabled?: boolean;
};

export function Button({
  text,
  width,
  height,
  color,
  backgroundColor,
  onClick,
  disabled,
}: Props) {
  return (
    <button
      onClick={onClick}
      disabled={disabled}
      css={{
        "display": "flex",
        "justifyContent": "center",
        "alignItems": "center",
        "width": width,
        "height": height,
        "backgroundColor": `${backgroundColor}`,
        "border": "none",
        "borderRadius": "8px",
        ":disabled": {
          opacity: buttonOpacity.disable,
        },
      }}>
      <Txt typography="displayBold14" color={color}>
        {text}
      </Txt>
    </button>
  );
}
