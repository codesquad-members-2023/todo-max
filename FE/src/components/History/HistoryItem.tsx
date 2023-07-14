import { colors } from "../../constants/colors";
import { Txt } from "../Txt";
import { HistoryItemData } from "./HistoryLayer";

export function HistoryItem({ history }: { history: HistoryItemData }) {
  return (
    <div
      css={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        width: "348px",
        height: "113px",
        padding: "16px",
        boxSizing: "border-box",
        gap: "16px",
        backgroundColor: colors.surfaceDefault,
      }}>
      <div
        className="profileImg"
        css={{
          width: "40px",
          height: "100%",
        }}>
        <img
          src="/icons/profileImg.png"
          alt="profileImg"
          css={{
            width: "40px",
            height: "40px",
            borderRadius: "50%",
            objectFit: "cover",
          }}
        />
      </div>
      <div
        className="historyBody"
        css={{
          display: "flex",
          flexDirection: "column",
          width: "260px",
          height: "100%",
          gap: "8px",
        }}>
        <div
          className="historyBodyUserName"
          css={{
            display: "flex",
            justifyContent: "start",
            width: "100%",
          }}>
          <Txt typography="displayMedium14" color={`${colors.textDefault}`}>
            @멋진박하
          </Txt>
        </div>
        <div
          className="historyBodyText"
          css={{
            // display: "flex",
            // flexWrap: "wrap",
            color: colors.textDefault,
            fontSize: "14px",
          }}>
          {generateHistoryText(history)}
        </div>
        <div
          className="historyBodyTime"
          css={{
            display: "flex",
            justifyContent: "start",
            width: "100%",
          }}>
          <Txt typography="displayMedium12" color={`${colors.textWeak}`}>
            3분전
          </Txt>
        </div>
      </div>
    </div>
  );
}

const generateHistoryText = (history: HistoryItemData) => {
  const boldStyle = {
    fontSize: "14px",
    fontWeight: 700,
    letterSpacing: "0em",
    color: "#4E4B66",
  };
  switch (history.action) {
    case "생성":
      return (
        <>
          <span style={boldStyle}>{history.title}</span>을(를){" "}
          <span style={boldStyle}>{history.at}</span>에{" "}
          <span style={boldStyle}>{history.action}</span>하였습니다.
        </>
      );
    case "삭제":
      return (
        <>
          <span style={boldStyle}>{history.title}</span>을(를){" "}
          <span style={boldStyle}>{history.at}</span>에서{" "}
          <span style={boldStyle}>{history.action}</span>하였습니다.
        </>
      );
    case "변경":
      return (
        <>
          <span style={boldStyle}>{history.title}</span>을(를){" "}
          <span style={boldStyle}>{history.action}</span>하였습니다.
        </>
      );
    case "이동":
      return (
        <>
          <span style={boldStyle}>{history.title}</span>을(를){" "}
          <span style={boldStyle}>{history.from}</span>
          에서 <span style={boldStyle}>{history.to}</span>로{" "}
          <span style={boldStyle}>{history.action}</span>하였습니다.
        </>
      );
    default:
      return null;
  }
};
