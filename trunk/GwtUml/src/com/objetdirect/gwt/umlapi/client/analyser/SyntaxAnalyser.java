package com.objetdirect.gwt.umlapi.client.analyser;

import com.objetdirect.gwt.umlapi.client.UMLDrawerException;

public abstract class SyntaxAnalyser {

	public static final int BEGIN = 0;
	public static final int FINISHED = -1;

	private int status = BEGIN;

	public LexicalAnalyser.Token process(LexicalAnalyser lex,
			LexicalAnalyser.Token tk) {
		while (getStatus() != FINISHED) {
			tk = processToken(lex, tk);
		}
		return tk;
	}

	int getStatus() {
		return status;
	}

	void setStatus(int status) {
		this.status = status;
	}

	protected abstract LexicalAnalyser.Token processToken(LexicalAnalyser lex,
			LexicalAnalyser.Token tk);

	protected void throwSyntaxError(LexicalAnalyser.Token tk) {
		throw new UMLDrawerException("Syntax error : " + tk);
	}

	protected void throwUnexpectedEOF() {
		throw new UMLDrawerException("Unexpected EOF");
	}

}
