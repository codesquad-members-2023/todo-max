/** @jsxImportSource @emotion/react */
import { HTMLAttributes } from "react";
import { fontSize, fontWeight } from "../constants/fonts";

interface Props extends HTMLAttributes<HTMLSpanElement> {
  typography?:
    | "displayBold24"
    | "displayBold16"
    | "displayBold14"
    | "displayBold12"
    | "displayMedium16"
    | "displayMedium14"
    | "displayMedium12"
    | "selectedBold16"
    | "selectedBold14"
    | "availableMedium16"
    | "availableMedium14";
  color: string;
}

export function Txt({
  typography = "displayMedium14",
  color,
  ...props
}: Props) {
  return (
    <span
      css={{
        display: "flex",
        // justifyContent: "center",
        // alignItems: "center",
        color,
        ...TYPOGRAPHY_VARIANT[typography],
      }}
      {...props}
    />
  );
}

const TYPOGRAPHY_VARIANT = {
  displayBold24: {
    fontSize: `${fontSize.large}px`,
    fontWeight: fontWeight.bold,
    lineHeight: "auto",
  },
  displayBold16: {
    fontSize: `${fontSize.medium}px`,
    fontWeight: fontWeight.bold,
    lineHeight: "auto",
  },
  displayBold14: {
    fontSize: `${fontSize.regular}px`,
    fontWeight: fontWeight.bold,
    lineHeight: "auto",
  },
  displayBold12: {
    fontSize: `${fontSize.small}px`,
    fontWeight: fontWeight.bold,
    lineHeight: "auto",
  },
  displayMedium16: {
    fontSize: `${fontSize.medium}px`,
    fontWeight: fontWeight.medium,
    lineHeight: "22px",
  },
  displayMedium14: {
    fontSize: `${fontSize.regular}px`,
    fontWeight: fontWeight.medium,
    lineHeight: "auto",
  },
  displayMedium12: {
    fontSize: `${fontSize.small}px`,
    fontWeight: fontWeight.medium,
    lineHeight: "auto",
  },
  selectedBold16: {
    fontSize: `${fontSize.medium}px`,
    fontWeight: fontWeight.bold,
    lineHeight: "auto",
  },
  selectedBold14: {
    fontSize: `${fontSize.regular}px`,
    fontWeight: fontWeight.bold,
    lineHeight: "auto",
  },
  availableMedium16: {
    fontSize: `${fontSize.medium}px`,
    fontWeight: fontWeight.medium,
    lineHeight: "auto",
  },
  availableMedium14: {
    fontSize: `${fontSize.regular}px`,
    fontWeight: fontWeight.medium,
    lineHeight: "auto",
  },
};
