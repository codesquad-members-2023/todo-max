import { ModalProvider } from "./contexts/ModalContext";
import { MainPage } from "./pages/MainPage";

function App() {
  return (
    <>
      <ModalProvider>
        <MainPage />
      </ModalProvider>
    </>
  );
}

export default App;
