import { colors } from "@constants/colors";
import { useState } from "react";
import { History } from "./History";
import { Button } from "./base/Button";
import { HistoryIcon } from "./icon/HistoryIcon";

export const Header = () => {
  const [isOpenHistory, setIsOpenHistory] = useState(false);

  const openHistory = () => {
    setIsOpenHistory(true);
  };

  const closeHistory = () => {
    setIsOpenHistory(false);
  };

  return (
    <div
      css={{
        display: "flex",
        justifyContent: "space-between",
        position: "relative",
      }}
    >
      <div>TODO LIST</div>
      <Button pattern="icon" onClick={openHistory}>
        <HistoryIcon size={24} rgb={colors.textDefault} />
      </Button>
      {isOpenHistory && <History closeHandler={closeHistory} />}
    </div>
  );
};
