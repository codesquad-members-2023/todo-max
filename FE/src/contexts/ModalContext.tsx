import { createContext, useState, ReactNode } from "react";
import { HistoryItemData } from "../components/Header/Header";

type ModalContextType = {
  removeCardState: boolean;
  setRemoveCardState: React.Dispatch<React.SetStateAction<boolean>>;
  alertType: "removeCard" | "removeHistory";
  setAlertType: React.Dispatch<
    React.SetStateAction<"removeCard" | "removeHistory">
  >;
  isAlertOpen: boolean;
  setIsAlertOpen: React.Dispatch<React.SetStateAction<boolean>>;
  historyData: HistoryItemData[];
  setHistoryData: React.Dispatch<React.SetStateAction<HistoryItemData[]>>;
};

type ModalProviderProps = {
  children: ReactNode;
};

export const ModalContext = createContext<ModalContextType | undefined>(
  undefined
);

export function ModalProvider({ children }: ModalProviderProps) {
  const [removeCardState, setRemoveCardState] = useState<boolean>(false);
  const [alertType, setAlertType] = useState<"removeCard" | "removeHistory">(
    "removeHistory"
  );
  const [isAlertOpen, setIsAlertOpen] = useState(false);
  const [historyData, setHistoryData] = useState<HistoryItemData[]>([]);
  const value: ModalContextType = {
    removeCardState,
    setRemoveCardState,
    alertType,
    setAlertType,
    isAlertOpen,
    setIsAlertOpen,
    historyData,
    setHistoryData,
  };

  return (
    <ModalContext.Provider value={value}>{children}</ModalContext.Provider>
  );
}
