import { colors } from "../../constants/colors";
import { Txt } from "../Txt";

export function NoHistory() {
  return (
    <div
      className="noHistory"
      css={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        width: "350px",
        height: "49px",
        padding: "16px",
        gap: "4px",
        boxSizing: "border-box",
      }}>
      <div
        css={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",

          width: "318px",
          height: "17px",
        }}>
        <Txt typography="displayMedium14" color={`${colors.textWeak}`}>
          사용자 활동 기록이 없습니다.
        </Txt>
      </div>
    </div>
  );
}
