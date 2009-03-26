package com.objetdirect.gwt.umlapi.client.analyser;

import java.util.ArrayList;
import java.util.List;

import com.objetdirect.gwt.umlapi.client.UMLDrawerException;
import com.objetdirect.gwt.umlapi.client.umlcomponents.Method;
import com.objetdirect.gwt.umlapi.client.umlcomponents.Parameter;

/**
 * @author  florian
 */
public class MethodSyntaxAnalyser extends SyntaxAnalyser {
	public static final int BEGIN_PARAMETER = 2;
	public static final int BEGIN_RETURN_TYPE = 5;
	public static final int END_PARAMETER = 4;
	public static final int OPEN_PARENTHESIS_EXPECTED = 1;
	public static final int PARAMETER_EXPECTED = 3;

	/**
	 * @uml.property  name="method"
	 * @uml.associationEnd  
	 */
	Method method = new Method(null, null, null);

	List<Parameter> parameters = new ArrayList<Parameter>();

	/**
	 * @return
	 * @uml.property  name="method"
	 */
	public Method getMethod() {
		return method;
	}

	void setParameters() {
		Parameter[] params = new Parameter[parameters.size()];
		for (int i = 0; i < params.length; i++)
			params[i] = parameters.get(i);
		//TODO method.setParameters(params);
	}
	@Override
	protected LexicalAnalyser.Token processToken(LexicalAnalyser lex,
			LexicalAnalyser.Token tk) {
		if (tk == null)
			tk = lex.getToken();
		switch (getStatus()) {
		case BEGIN:
			if (tk == null)
				throwUnexpectedEOF();
			else if (tk.getType() == LexicalAnalyser.IDENTIFIER) {
				method.setName(tk.getContent());
				setStatus(OPEN_PARENTHESIS_EXPECTED);
				return null;
			} else
				throwSyntaxError(tk);
		case OPEN_PARENTHESIS_EXPECTED:
			if (tk == null)
				throwUnexpectedEOF();
			else if (tk.getType() == LexicalAnalyser.SIGN
					&& tk.getContent().equals("(")) {
				setStatus(BEGIN_PARAMETER);
				return null;
			} else
				throwSyntaxError(tk);
		case BEGIN_PARAMETER:
			if (tk == null)
				throwUnexpectedEOF();
			else if (tk.getType() == LexicalAnalyser.SIGN
					&& tk.getContent().equals(")")) {
				setStatus(BEGIN_RETURN_TYPE);
				return null;
			} else {
				ParameterAnalyser pa = new ParameterAnalyser();
				tk = pa.process(lex, tk);
				parameters.add(pa.getParameter());
				setStatus(END_PARAMETER);
				return tk;
			}
		case END_PARAMETER:
			if (tk == null)
				throwUnexpectedEOF();
			else if (tk.getType() == LexicalAnalyser.SIGN
					&& tk.getContent().equals(")")) {
				setStatus(BEGIN_RETURN_TYPE);
				return null;
			} else if (tk.getType() == LexicalAnalyser.SIGN
					&& tk.getContent().equals(",")) {
				setStatus(PARAMETER_EXPECTED);
				return null;
			} else
				throwSyntaxError(tk);
		case PARAMETER_EXPECTED:
			if (tk == null)
				throwUnexpectedEOF();
			else {
				ParameterAnalyser pa = new ParameterAnalyser();
				tk = pa.process(lex, tk);
				parameters.add(pa.getParameter());
				setStatus(END_PARAMETER);
				return tk;
			}
		case BEGIN_RETURN_TYPE:
			setParameters();
			if (tk != null && tk.getType() == LexicalAnalyser.SIGN
					&& tk.getContent().equals(":")) {
				TypeAnalyser ta = new TypeAnalyser();
				tk = ta.process(lex, null);
				method.setReturnType(ta.getType());
				setStatus(FINISHED);
				return tk;
			} else {
				setStatus(FINISHED);
				return tk;
			}
		}
		throw new UMLDrawerException("Invalid syntax status : " + getStatus());
	}
}
